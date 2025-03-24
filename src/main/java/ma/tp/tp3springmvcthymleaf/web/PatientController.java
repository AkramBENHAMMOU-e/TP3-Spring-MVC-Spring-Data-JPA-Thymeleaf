package ma.tp.tp3springmvcthymleaf.web;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import ma.tp.tp3springmvcthymleaf.entities.Patient;
import ma.tp.tp3springmvcthymleaf.repository.PatientRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller @AllArgsConstructor
public class PatientController {
    private PatientRepository patientRepository;
    @GetMapping("/user/index")
    public String index(Model model ,
                        @RequestParam(name ="page", defaultValue = "0") int p,
                        @RequestParam(name ="size", defaultValue = "4")  int s,
                        @RequestParam(name ="keyword", defaultValue = "") String kw) {
        Page<Patient> pagePatients = patientRepository.findByNomContains(kw, PageRequest.of(p, s));
        model.addAttribute("patients", pagePatients.getContent());
        model.addAttribute("pages", new int[pagePatients.getTotalPages()]);
        model.addAttribute("currentPage", p);
        model.addAttribute("keyword", kw);
        return "patient";
    }

    @GetMapping("/admin/delete")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String delete(Long id, String keyword, int page) {
        patientRepository.deleteById(id);
        return "redirect:/user/index?page="+page+"&keyword="+keyword;
    }

    @GetMapping("/admin/patients")
    @ResponseBody
public List<Patient> listPatients() {
        return patientRepository.findAll();
    }

@GetMapping("/admin/formPatients")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public String formPatient(Model model) {
        model.addAttribute("patient", new Patient());
        return "formPatients";
    }

    @GetMapping("/admin/editPatients")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String editPatients(Model model, Long id,String keyword,int page) {
        Patient patient = patientRepository.findById(id).orElse(null);
        if (patient == null) {throw new RuntimeException("Patient not found");}
        model.addAttribute("patient", patient);
        model.addAttribute("keyword", keyword);
        model.addAttribute("currentPage", page);
        return "editPatients";
    }

@PostMapping("/admin/save")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public String savePatient(Model model, @Valid Patient patient , BindingResult bandingResult,@RequestParam(defaultValue = "") String keyword,@RequestParam(defaultValue = "0") int page) {
        if (bandingResult.hasErrors()) return "formPatients";
        patientRepository.save(patient);
        return "redirect:/user/index?page="+page+"&keyword="+keyword;
        }

        @GetMapping("/")
    public String home() {
        return "redirect:/user/index";
        }
        }

