package com.Ctrl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.Services.PlazasService;
import com.Services.ConfiguracionService;
import com.Services.FacturasService;
import com.Services.PersonasService;
import com.TO.Configuracion;
import com.TO.Facturas;
import com.TO.Plazas;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Controller
public class FacturasCtrl {

    @Autowired
    private FacturasService facturasService;
    @Autowired
    private PlazasService plazasService;
    @Autowired
    private PersonasService personasService;
    @Autowired
    private ConfiguracionService configuracionService;

    /******************* METODOS ENTRADAS *******************/

    // INICIO ENTRADAS
    @GetMapping("/entradas")
    public String entradas(Model model) {
        model.addAttribute("titulo", "Entradas");
        iniciar(model);
        var facturas = facturasService.listarVehiculosParqueados();
        model.addAttribute("facturas", facturas);
        return "Entradas";
    }

    // listarPlazasMotos
    @GetMapping("/entradas/listarPlazasMoto")
    public String listarPlazasMoto(Model model) {
        var plazas = plazasService.plazasLibresxTipo("Moto");
        model.addAttribute("plazas", plazas);
        iniciar(model);
        var facturas = facturasService.listarVehiculosParqueados();
        model.addAttribute("facturas", facturas);
        return "Entradas";
    }

    // listarPlazasCarros
    @GetMapping("/entradas/listarPlazasCarro")
    public String listarPlazasCarro(Model model) {
        var plazas = plazasService.plazasLibresxTipo("Carro");
        model.addAttribute("plazas", plazas);
        iniciar(model);
        var facturas = facturasService.listarVehiculosParqueados();
        model.addAttribute("facturas", facturas);
        return "Entradas";
    }

    // GUARDAR ENTRADA
    @PostMapping("/entradas/guardarEntrada")
    public String guardarEntrada(Facturas factura) {
        factura.setPersona(personasService.buscarPersona(2));
        Calendar calendar = Calendar.getInstance();
        factura.setFechaEntrada(calendar.getTime());
        factura.setTipoContrato("normal");
        facturasService.guardar(factura);
        Plazas plaza = factura.getPlaza();
        plaza.setEstado("ocupada");
        plazasService.guardar(plaza);
        return "redirect:/entradas";
    }

    /******************* METODOS SALIDAS *******************/

    // INICIO SALIDAS
    @GetMapping("/salidas")
    public String salidas(Model model) {
        model.addAttribute("titulo", "Salidas");
        iniciar(model);
        var facturas = facturasService.listarVehiculosParqueados();
        model.addAttribute("facturas", facturas);
        return "Salidas";
    }

    // VER
    @GetMapping("/salidas/ver/{idFacturas}")
    public String ver(Facturas factura, Model model) {
        factura = facturasService.buscarFactura(factura.getIdFacturas());
        model.addAttribute("factura", factura);
        var facturas = facturasService.listarVehiculosParqueados();
        model.addAttribute("facturas", facturas);
        factura.setValor(calcularValor(factura, model));
        return "Salidas";
    }

    // DAR SALIDA POST
    @PostMapping("/salidas/darSalidaPost/{idFacturas}")
    public String darSalidaPost(Facturas factura, Model model) {
        darSalida(factura, model);
        return "redirect:/facturas";
    }

    // DAR SALIDA GET
    @GetMapping("/salidas/darSalidaGet/{idFacturas}")
    public String darSalidaGet(Facturas factura, Model model) {
        darSalida(factura, model);
        return "redirect:/facturas";
    }

    private void darSalida(Facturas factura, Model model) {
        factura = facturasService.buscarFactura(factura.getIdFacturas());
        
        factura.setCodigoFactura(obtenerCodigoFactura(factura.getIdFacturas()));
        factura.setValor(calcularValor(factura, model));
        facturasService.guardar(factura);
        Plazas plaza = factura.getPlaza();
        plaza.setEstado("libre");
        plazasService.guardar(plaza);
    }

    private String obtenerCodigoFactura(int idFactura) {
        String id = String.valueOf(idFactura);
        String codigo = id;
        for (int i = 0; i < 10 - id.length(); i++) {
            codigo = "0" + codigo;
        }
        return codigo;
    }

    private double calcularValor(Facturas factura, Model model) {
        Double valor = 0.0;
        Calendar calendar = Calendar.getInstance();
        Date now = calendar.getTime();
        factura.setFechaFactura(now);
        factura.setFechaSalida(now);
        String tipo = factura.getPlaza().getTipo();
        Long tiempo = factura.getFechaSalida().getTime() - factura.getFechaEntrada().getTime();
        TimeUnit tu = TimeUnit.HOURS;
        Long horas = tu.convert(tiempo, TimeUnit.MILLISECONDS);
        tu = TimeUnit.MINUTES;
        long minutos = tu.convert(tiempo, TimeUnit.MILLISECONDS) - (horas * 60);
        String periodo = horas + " hora(s), " + minutos + " minuto(s)";
        model.addAttribute("periodo", periodo);
        Configuracion config = configuracionService.buscarConfiguracion();

        if (horas == 0) {
            if (minutos <= 15) {
                if (tipo.equals("Carro")) {
                    valor = config.getValorFracCarro();
                } else {
                    valor = config.getValorFracMoto();
                }
            } else {
                if (tipo.equals("Carro")) {
                    valor = config.getValorHoraCarro();
                } else {
                    valor = config.getValorHoraMoto();
                }
            }
        } else {
            if (minutos <= 10) {
                if (tipo.equals("Carro")) {
                    valor = config.getValorHoraCarro();
                } else {
                    valor = config.getValorHoraMoto();
                }
            } else {
                if (tipo.equals("Carro")) {
                    valor = config.getValorHoraCarro() * (horas + 1);
                } else {
                    valor = config.getValorHoraMoto() * (horas + 1);
                }
            }
        }
        return valor;
    }

    // BUSCAR
    @PostMapping("/salidas/buscar")
    public String buscar(Model model, Facturas facturaBuscada) {
        Facturas factura = facturasService.buscarVehiculoParqueado(facturaBuscada.getPlaca());
        model.addAttribute("factura", factura);
        var facturas = facturasService.listarVehiculosParqueados();
        model.addAttribute("facturas", facturas);
        factura.setValor(calcularValor(factura, model));
        return "Salidas";
    }

    /******************* METODOS FACTURAS *******************/

    // INICIO FACTURAS
    @GetMapping("/facturas")
    public String facturas(Model model) {
        model.addAttribute("titulo", "Facturas");
        iniciar(model);
        var facturas = facturasService.listarFacturas();
        model.addAttribute("facturas", facturas);
        return "Facturas";
    }
    /******************* METODOS GENERALES *******************/

    private void iniciar(Model model) {
        if (model.getAttribute("factura") == null) {
            Facturas factura = new Facturas();
            model.addAttribute("factura", factura);
        }
    }

    // VER
    @GetMapping("/facturas/ver/{idFacturas}")
    public String verFactura(Facturas factura, Model model) {
        factura = facturasService.buscarFactura(factura.getIdFacturas());
        model.addAttribute("factura", factura);
        var facturas = facturasService.listarFacturas();
        model.addAttribute("facturas", facturas);
        if(factura.getValor() == null) 
            factura.setValor(calcularValor(factura, model));
        return "Facturas";
    }

    /**********************************************************************/

}
