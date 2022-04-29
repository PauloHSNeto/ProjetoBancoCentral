package ibm.itau.projetobancocentral.controllers;

import ibm.itau.projetobancocentral.services.OnboardingServices;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/onboarding")
@AllArgsConstructor
public class OnboardingController {
    @Autowired
    private OnboardingServices onboardingServices;

    @PostMapping
    public ResponseEntity<String> onboarding(@RequestBody String url) {
        onboardingServices.onboarding(url);
        return ResponseEntity.ok("Onboarding realizado com sucesso!");
    }
    @DeleteMapping
    public ResponseEntity<String> deleteOnboarding() {
        onboardingServices.deleteOnboarding();
        return ResponseEntity.ok("Deletado");
    }
}
