package jakwagne.pigeonapp;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/pigeon")
public class PigeonController {

    private final PigeonRepository repository;

    public PigeonController(PigeonRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public String getPigeonList(Model model){
        model.addAttribute("pigeonDto", new PigeonDto(null, null, 0));
        model.addAttribute("pigeons",repository.findAll());
        return "pigeonList";
    }

    @GetMapping("/{id}")
    public String getPigeon(@PathVariable String id, Model model){
        repository.findById(id).ifPresent(pigeon -> {
            model.addAttribute("pigeon", pigeon.toDto());
            Optional.ofNullable(pigeon.getMotherId()).flatMap(repository::findById).ifPresent(motherDao -> {
                model.addAttribute("mother", motherDao.toDto());
                Optional.ofNullable(motherDao.getMotherId()).flatMap(repository::findById).ifPresent(motherMotherDao -> {
                    model.addAttribute("motherMother", motherMotherDao.toDto());
                });
                Optional.ofNullable(motherDao.getFatherId()).flatMap(repository::findById).ifPresent(motherFatherDao -> {
                    model.addAttribute("motherFather", motherFatherDao.toDto());
                }) ;
            });

            Optional.ofNullable(pigeon.getFatherId()).flatMap(repository::findById).ifPresent(fatherDao -> {
                model.addAttribute("father", fatherDao.toDto());
                Optional.ofNullable(fatherDao.getMotherId()).flatMap(repository::findById).ifPresent(fatherMotherDao -> {
                    model.addAttribute("fatherMother", fatherMotherDao.toDto());
                });
                Optional.ofNullable(fatherDao.getFatherId()).flatMap(repository::findById).ifPresent(fatherFatherDao -> {
                    model.addAttribute("fatherFather", fatherFatherDao.toDto());
                }) ;
            });
        });
        model.addAttribute("childrenList",repository.findPigeonsByParentId(id));
        return "pigeon";
    }

    @PostMapping
    public String postPigeon(final PigeonDto pigeonDto){
        repository.save(pigeonDto.toPigeon());
        return "redirect:/pigeon";
    }

    @GetMapping("/{id}/createParent")
    public String createParent(@PathVariable String id, @RequestParam("gender") int gender, Model model){
        model.addAttribute("childId",id);
        model.addAttribute("pigeonDto", new PigeonDto(null, null, gender));
        return "createParentForm";
    }

    @PostMapping("/{id}/createParent")
    public String postCreateParent(@PathVariable String id,final PigeonDto pigeonDto){
        Pigeon child = repository.findById(id).orElseThrow(IllegalArgumentException::new);
        switch (pigeonDto.gender()) {
            case 1 -> child.setFatherId(pigeonDto.id());
            case 2 -> child.setMotherId(pigeonDto.id());
            default -> throw new IllegalArgumentException(String.valueOf(pigeonDto.gender()));
        }
        repository.save(child);
        repository.save(pigeonDto.toPigeon());
        return "redirect:/pigeon/"+id;
    }

}
