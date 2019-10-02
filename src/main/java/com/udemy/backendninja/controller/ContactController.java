package com.udemy.backendninja.controller;

import com.udemy.backendninja.constant.ViewConstant;
import com.udemy.backendninja.entity.Contact;
import com.udemy.backendninja.entity.User;
import com.udemy.backendninja.service.ContactService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/contacts")
public class ContactController {

    private static final Log LOG = LogFactory.getLog(ContactController.class);

    @Autowired
    @Qualifier("contactServiceImpl")
    private ContactService contactService;

    @GetMapping("/cancel")
    public String cancel(){
        return "redirect:/contacts/showcontacts";
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/contactform")
    private String redirectContactForm(@RequestParam(name="id", required = false)int id,
            Model model){

        Contact contact = new Contact();

        if(id != 0){
            LOG.info("Method: redirectContactForm() -- params: contact=" + contact.toString());
            contact = contactService.findContactById(id);
            contact.setId(id);
            LOG.info("Method: redirectContactForm() -- params: contact=" + contact.toString());

        }

        model.addAttribute("contact",contact);
        return ViewConstant.CONTACT_FORM;
    }

    @PostMapping("/addcontact")
    public String addContact(@ModelAttribute(name="contact") Contact contact,
                             Model model){
        LOG.info("Method: addContact() -- params: contact=" + contact.toString());

        if(contactService.addContact(contact) != null){
            model.addAttribute("result",1);
        }
        else{
            model.addAttribute("result",0);
        }
        return "redirect:/contacts/showcontacts";
    }

    @GetMapping("/showcontacts")
    public ModelAndView showContacts(){
        ModelAndView mav = new ModelAndView(ViewConstant.CONTACTS);

        //User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Authentication user = SecurityContextHolder.getContext().getAuthentication();
        mav.addObject("username",user.getName());
        mav.addObject("contacts",contactService.listAllContacts());
        return mav;
    }

    @GetMapping("/removecontact")
    public ModelAndView removeContact(@RequestParam(name="id", required = true)int id){
        contactService.removeContact(id);
        return showContacts();
    }
}
