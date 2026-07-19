package com.example.buyorrentacar.web;

import com.example.buyorrentacar.model.Avtomobil;
import com.example.buyorrentacar.model.Covek;
import com.example.buyorrentacar.model.Naracka;
import com.example.buyorrentacar.model.exceptions.InvalidAvtomobilIdxception;
import com.example.buyorrentacar.repository.NarackaRepository;
import com.example.buyorrentacar.service.AvtomobilService;
import com.example.buyorrentacar.service.CovekService;
import com.example.buyorrentacar.service.NarackaService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/naracki")
public class NarackiController {

    private final NarackaService narackaService;
    private final CovekService covekService;
    private final AvtomobilService avtomobilService;
    private final NarackaRepository narackaRepository;


    public NarackiController(NarackaService narackaService, CovekService covekService, AvtomobilService avtomobilService, NarackaRepository narackaRepository) {
        this.narackaService = narackaService;
        this.covekService = covekService;
        this.avtomobilService = avtomobilService;
        this.narackaRepository = narackaRepository;
    }

    @GetMapping
    public String getNarackiPage(Model model, Authentication authentication, HttpServletRequest request)
    {
        Covek logiran = (Covek) authentication.getPrincipal();
        List<Naracka> narackiPending = this.narackaService.listByIdNaSopstvenikAndStatus(logiran.getId(),false);
        List<Naracka> narackiPrif = this.narackaService.listByIdNaSopstvenikAndStatus(logiran.getId(), true);
        model.addAttribute("narackiPending",narackiPending);
        model.addAttribute("naracki",narackiPrif);
        model.addAttribute("bodyContent","listaNaracki");
        return "master-template";
    }
    @PostMapping("/success/{id}")
    public String updateNaracka(@PathVariable Long id, Model model)
    {
        this.narackaService.update(id);
        return "redirect:/naracki";
    }
//    @GetMapping("/success")
//    public String getSuccessNaracka(Model model)
//    {
//        model.addAttribute("bodyContent","successs");
//        return "master-template";
//    }
    @GetMapping("/detali/{Id}")
    public String getDetailsPage(@PathVariable Long Id,Model model)
    {
        Covek covek = this.covekService.findById(Id);
        model.addAttribute("covek",covek);
        model.addAttribute("bodyContent","details");
        return "master-template";

    }

    @DeleteMapping("/delete/{id}")
    public  String deleteNaracka(@PathVariable Long id)
    {
        this.narackaService.delete(id);
        return "redirect:/naracki";
    }
    //----------------------------------------------------

    @GetMapping("/moiAvtomobili")
    public String getMoiAvtomobiliPage(Model model,Authentication authentication)
    {
        Covek logiran = (Covek)authentication.getPrincipal();
        List<Avtomobil> avtomobilList = this.avtomobilService.listByUserId(logiran.getId());
        model.addAttribute("avtomobili",avtomobilList);
        model.addAttribute("bodyContent","moiAvtomobili");
        return "master-template";
    }
    @GetMapping("/moiAvtomobili/add-form")
    public String showAddCarPage(Model model)
    {
        model.addAttribute("bodyContent","add-car");
        return "master-template";
    }
    @GetMapping("/moiAvtomobili/edit-form/{Id}")
    public String showAddCarPage(@PathVariable Long Id, Model model)
    {
        Avtomobil avtomobil = this.avtomobilService.findById(Id).orElseThrow(()->new InvalidAvtomobilIdxception(Id));
        model.addAttribute("avtomobil",avtomobil);
        model.addAttribute("bodyContent","add-car");
        return "master-template";
    }
    @PostMapping("/moiAvtomobili/")
    public String createCar(@RequestParam String marka,
                            @RequestParam String model,
                            @RequestParam String menuvac,
                            @RequestParam String boja,
                            @RequestParam Integer kilometraza,
                            @RequestParam Integer godinaProizvodstvo,
                            @RequestParam String tipGorivo,
                            @RequestParam String kategorija,
                            @RequestParam Integer cena,
                            Authentication authentication)
    {
        Covek logiran = (Covek) authentication.getPrincipal();
        this.avtomobilService.create(marka,model,menuvac,boja,kilometraza,godinaProizvodstvo,tipGorivo,kategorija,cena,logiran.getId());
        return "redirect:/naracki/moiAvtomobili";
    }
    @PostMapping("/moiAvtomobili/{id}")
    public String updateCar(@PathVariable Long id,
                            @RequestParam String marka,
                            @RequestParam String model,
                            @RequestParam String menuvac,
                            @RequestParam String boja,
                            @RequestParam Integer kilometraza,
                            @RequestParam Integer godinaProizvodstvo,
                            @RequestParam String tipGorivo,
                            @RequestParam String kategorija,
                            @RequestParam Integer cena,
                            Authentication authentication)
    {
        Covek logiran = (Covek) authentication.getPrincipal();
        this.avtomobilService.update(id, marka, model, menuvac, boja, kilometraza, godinaProizvodstvo, tipGorivo, kategorija, cena);
        return "redirect:/naracki/moiAvtomobili";
    }
    @DeleteMapping("/moiAvtomobili/{id}")
    public String deleteCar(@PathVariable Long id)
    {
        this.avtomobilService.deleteById(id);
        return "redirect:/naracki/moiAvtomobili";

    }


}
