package com.example.buyorrentacar.web;

import com.example.buyorrentacar.model.Avtomobil;
import com.example.buyorrentacar.model.Covek;
import com.example.buyorrentacar.model.Naracka;
import com.example.buyorrentacar.model.exceptions.InvalidAvtomobilIdxception;
import com.example.buyorrentacar.service.AvtomobilService;
import com.example.buyorrentacar.service.CovekService;
import com.example.buyorrentacar.service.NarackaService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping({"/avtomobili","/"})
public class AvtomobiliController {

    private final AvtomobilService avtomobilService;
    private final NarackaService narackaService;
    private final CovekService covekService;

    public AvtomobiliController(AvtomobilService avtomobilService, NarackaService narackaService, CovekService covekService) {
        this.avtomobilService = avtomobilService;
        this.narackaService = narackaService;
        this.covekService = covekService;
    }
    @GetMapping
    public String getAvtomobiliPage(Model model)
    {
        List<Avtomobil> avtomobili = this.avtomobilService.listAll();
        model.addAttribute("avtomobili",avtomobili);
        model.addAttribute("bodyContent","listaAvtomobili");
        return "master-template";
    }
    @DeleteMapping("/delete/{Id}")
    public String deleteProduct(@PathVariable Long Id)
    {
        this.avtomobilService.deleteById(Id);
        return "redirect:/avtomobili";
    }
    @PostMapping("/naracaj/{Id}/{Covek}/{iznos}")
    public String createNaracka(@PathVariable String Id,
                                @PathVariable String Covek,
                                @PathVariable String iznos,
                                Authentication authentication)
    {
        Covek logiran = (Covek) authentication.getPrincipal();
        LocalDate date = LocalDate.now();
        Naracka naracka = this.narackaService.create(date,iznos,false,false,Long.parseLong(Covek), logiran.getId(), Long.parseLong(Id));

        return "redirect:/avtomobili/moiNaracki";

    }
    @GetMapping("/moiNaracki")
    public String showMoiNarackiPage(Model model,Authentication authentication)
    {
        Covek logiran = (Covek) authentication.getPrincipal();
        List<Naracka> narackiPending = this.narackaService.listByIdNaKupuvacAndStatus(logiran.getId(),false);
        List<Naracka> narackiP = this.narackaService.listByIdNaKupuvacAndStatus(logiran.getId(),true);
        model.addAttribute("narackiPending",narackiPending);
        model.addAttribute("naracki",narackiP);
        model.addAttribute("bodyContent","moiNaracki");
        return "master-template";
    }
    @GetMapping("/moiNaracki/detali/{Id}")
    public String getSopstvenikDetails(@PathVariable Long Id,Model model)
    {
        Covek covek = this.covekService.findById(Id);
        model.addAttribute("covek",covek);
        model.addAttribute("bodyContent","detailsSopstvenik");
        return "master-template";
    }
    @GetMapping("/moiNaracki/{id}/payment")
    public String paymentPage(@PathVariable Long id,Model model)
    {
        Naracka naracka = this.narackaService.findById(id);
        model.addAttribute("naracka",naracka);
        model.addAttribute("bodyContent","payment");
        return "master-template";
    }
    @PostMapping("/moiNaracki/{id}/paymentSuccess")
    public String succeessPaymentPage(@PathVariable Long id,Model model)
    {
        this.narackaService.plati(id);
        return "redirect:/avtomobili/success";
    }
    @GetMapping("/success")
    public String success(Model model)
    {
        model.addAttribute("bodyContent","success");
        return "master-template";
    }
}
