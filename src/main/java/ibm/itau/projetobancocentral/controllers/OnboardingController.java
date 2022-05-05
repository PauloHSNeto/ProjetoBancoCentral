package ibm.itau.projetobancocentral.controllers;

import ibm.itau.projetobancocentral.services.OnboardingServices;
import ibm.itau.projetobancocentral.services.ValueServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/onboarding")
@Tag(name = "Onboarding", description = "Onboarding Client that populates takes the data and populates the database")
public class OnboardingController {
    @Autowired
    private OnboardingServices onboardingServices;
    @Autowired
    private ValueServices valueServices;

    @Operation(summary = "Send Url to the application", description = "Recieves the url of the Central Banks`s API and populates de the database with the results", tags = {"Onboarding"})
    @PostMapping
    public ResponseEntity<String> onboarding(@RequestBody String url) {
        onboardingServices.onboarding(url);
        valueServices.updateDifference();
        return ResponseEntity.ok("Onboarding realizado com sucesso!");
    }
    @Operation(summary = "Removes all data from the databse", description = "Deletes all values form database", tags = {"Onboarding"})
    @DeleteMapping
    public ResponseEntity<String> deleteOnboarding() {
        onboardingServices.deleteOnboarding();
        return ResponseEntity.ok("Deletado");
    }
}
