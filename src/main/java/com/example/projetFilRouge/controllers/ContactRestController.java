package com.example.projetFilRouge.controllers;

import com.example.projetFilRouge.exceptions.ResourceNotFoundException;
import com.example.projetFilRouge.models.ContactDTO;
import com.example.projetFilRouge.services.ContactService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/contacts")
@RequiredArgsConstructor
@Slf4j
public class ContactRestController {
    private final ContactService contactService;

    @GetMapping
    public List<ContactDTO> listContacts(){
        List<ContactDTO> contacts = contactService.getContacts();

        return contacts;
    }

    @GetMapping("/{contactId}")
    public ContactDTO getDetails (@PathVariable("contactId") UUID id){
        Optional<ContactDTO> foundContact = contactService.getContactById(id);

        if (foundContact.isPresent()) {
            return foundContact.get();
        }

        throw new ResourceNotFoundException();
    }

    @PostMapping("/add")
    public ResponseEntity<String> addContact(@RequestBody ContactDTO newContact) {
        ContactDTO createdContact = contactService.addContact(newContact);

        return new ResponseEntity<String>("Contact created! New id: " + createdContact.getId(), HttpStatus.CREATED);
    }

    @DeleteMapping("/{contactId}")
    public ResponseEntity<String> deleteContact(@PathVariable("contactId") UUID id) {
        Optional<ContactDTO> foundContact = contactService.getContactById(id);

        if (foundContact.isPresent()) {

            if(contactService.deleteContactById(id)) {
                return new ResponseEntity<String>("Contact deleted at id: " + id, HttpStatus.OK);
            }

            return new ResponseEntity<String>("Something went wrong...", HttpStatus.NOT_MODIFIED);

        } else {
            throw new ResourceNotFoundException();
        }
    }

    @PatchMapping("/{contactId}")
    public ContactDTO editContact(@PathVariable("contactId") UUID id, @RequestBody ContactDTO contactData) {
        Optional<ContactDTO> foundContact = contactService.getContactById(id);

        if (foundContact.isPresent()) {
            return contactService.editContact(id, contactData);
        } else {
            throw new ResourceNotFoundException();
        }
    }
}
