package com.example.projetFilRouge.controllers;


import com.example.projetFilRouge.exceptions.ResourceNotFoundException;
import com.example.projetFilRouge.models.ContactDTO;
import com.example.projetFilRouge.services.ContactService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("/contacts")
@RequiredArgsConstructor
@Slf4j
public class ContactController {

    private final ContactService contactService;

    @GetMapping
    public String listContacts(Model model) {
        List<ContactDTO> contacts = contactService.getContacts();

        model.addAttribute("contacts", contacts);

        return "contacts/list";
    }

    @GetMapping("/mot")
    public String listContactsBy(Model model, @RequestParam(value = "mot", defaultValue = "") String mot) {
        List<ContactDTO> contacts = contactService.getContactsBy(mot);

        model.addAttribute("contacts", contacts);

        return "contacts/list";
    }

    @GetMapping("/{contactId}")
    public String getContactDetails(@PathVariable("contactId") UUID id, Model model) {
       Optional<ContactDTO> foundContact = contactService.getContactById(id);

        if (foundContact.isPresent()) {
            model.addAttribute("contact", foundContact.get());
            model.addAttribute("mode", "details");

            return "contacts/contactForm";
        }

        throw new ResourceNotFoundException();
    }

    @GetMapping("/add")
    public String getContactForm(Model model) {

        model.addAttribute("contact", ContactDTO.builder().build());
        model.addAttribute("mode", "add");

        return "contacts/contactForm";
    }

    @PostMapping("/add")
    public String addContact(ContactDTO newContact) {
        contactService.addContact(newContact);

        return "redirect:/contacts";
    }

    @GetMapping ("/delete/{contactId}")
    public String deleteContactDetails(@PathVariable("contactId") UUID id) {


        contactService.deleteContactById(id);


        return "redirect:/contacts";

    }

    @GetMapping("/edit/{contactId}")
    public String editContactForm(Model model, @PathVariable("contactId") UUID id) {

        Optional<ContactDTO> foundContact = contactService.getContactById(id);

        model.addAttribute("contact", foundContact.get());
        model.addAttribute("mode", "add");

        return "contacts/contactForm";
    }

    @PostMapping  ("/edit/{contactId}")
    public String patchContact(ContactDTO newContact, @PathVariable("contactId") UUID id ) {
        contactService.editContact(id, newContact);

        return "redirect:/contacts";
    }
}
