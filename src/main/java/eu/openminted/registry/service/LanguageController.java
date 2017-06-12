package eu.openminted.registry.service;

import eu.openminted.registry.domain.Language;
import eu.openminted.registry.domain.LanguageDescription;
import eu.openminted.registry.exception.ResourceNotFoundException;
import eu.openminted.registry.exception.ServerError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Base64;

/**
 * Created by stefanos on 13/1/2017.
 */
@RestController
@RequestMapping("/request/language")
public class LanguageController {

    @Autowired
    ResourceCRUDService<LanguageDescription> languageService;

    @RequestMapping(value = "{id}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public ResponseEntity<LanguageDescription> getLanguage(@PathVariable("id") String id) {
        String id_decoded = new String(Base64.getDecoder().decode(id));
        LanguageDescription language = languageService.get(id_decoded);
        if(language == null)
            throw new ResourceNotFoundException();
        else
            return new ResponseEntity<>(language, HttpStatus.OK);

    }

    @RequestMapping(method = RequestMethod.POST, headers = "Accept=application/json; charset=utf-8")
    public ResponseEntity<String> addLanguageJson(@RequestBody LanguageDescription language) {

        languageService.add(language);
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @RequestMapping(method = RequestMethod.POST, headers = "Accept=application/xml; charset=utf-8")
    public ResponseEntity<String> addLanguageXml(@RequestBody LanguageDescription language) {

        languageService.add(language);
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @RequestMapping(method = RequestMethod.PUT, headers = "Accept=application/json; charset=utf-8")
    public ResponseEntity<String> updateLanguage(@RequestBody LanguageDescription language) {

        languageService.update(language);
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @RequestMapping(method = RequestMethod.DELETE, headers = "Accept=application/json; charset=utf-8")
    public ResponseEntity<String> deleteLanguage(@RequestBody LanguageDescription language) {

        languageService.delete(language);
        return new ResponseEntity<>(HttpStatus.OK);

    }
} 
