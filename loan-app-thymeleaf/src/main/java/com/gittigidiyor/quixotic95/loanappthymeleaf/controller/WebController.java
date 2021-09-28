package com.gittigidiyor.quixotic95.loanappthymeleaf.controller;


import com.gittigidiyor.quixotic95.loanappthymeleaf.client.CreditScoreClient;
import com.gittigidiyor.quixotic95.loanappthymeleaf.client.CustomerClient;
import com.gittigidiyor.quixotic95.loanappthymeleaf.client.LoanApplicationClient;
import com.gittigidiyor.quixotic95.loanappthymeleaf.client.SmsClient;
import com.gittigidiyor.quixotic95.loanappthymeleaf.model.Customer;
import com.gittigidiyor.quixotic95.loanappthymeleaf.model.LoanApplicationResult;
import com.gittigidiyor.quixotic95.loanappthymeleaf.model.TcknModel;
import com.gittigidiyor.quixotic95.loanappthymeleaf.validation.group.OrderedChecks;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/customers")
@RequiredArgsConstructor
public class WebController {

    private final CustomerClient customerClient;
    private final CreditScoreClient creditScoreClient;
    private final LoanApplicationClient loanApplicationClient;
    private final SmsClient smsClient;

    @GetMapping("/list")
    public String listCustomers(Model model) {

        List<Customer> customers = customerClient.findAllCustomers().getBody();

        model.addAttribute("customers", customers);

        return "web/customer-list";

    }

    @GetMapping("/add")
    public String showSaveCustomerForm(Model model) {

        Customer customer = new Customer();

        model.addAttribute("customer", customer);

        return "web/customer-create-form";

    }

    @PostMapping("/add")
    public String saveCustomer(@ModelAttribute("customer") @Validated(OrderedChecks.class) Customer customer, BindingResult result) {

        if (result.hasErrors()) {
            return "web/customer-create-form";
        }

        customerClient.saveCustomer(customer);
        creditScoreClient.generateCreditScoreByTckn(customer.getTckn());

        return "redirect:/customers/list";

    }

    @GetMapping("/update")
    public String showUpdateCustomerForm(@RequestParam("customerId") UUID customerId, Model model) {

        Customer customer = customerClient.findCustomerById(customerId).getBody();

        model.addAttribute("customer", customer);

        return "web/customer-update-form";

    }

    @PostMapping("/update")
    public String updateCustomer(@ModelAttribute("customer") @Validated(OrderedChecks.class) Customer customer, BindingResult result) {

        if (result.hasErrors()) {
            return "web/customer-update-form";
        }

        customerClient.updateCustomer(customer);

        return "redirect:/customers/list";

    }

    @GetMapping("/loanApplicationForm")
    public String showLoanApplicationForm(Model model) {

        TcknModel tcknModel = new TcknModel();

        model.addAttribute("tcknModel", tcknModel);

        return "web/loan-application-form";

    }

    @PostMapping("/loanApplicationForm")
    public String applyForLoan(@ModelAttribute("tcknModel") @Validated(OrderedChecks.class) TcknModel tcknModel, BindingResult result, RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            return "web/loan-application-form";
        }

        loanApplicationClient.applyCustomerForLoan(tcknModel.getTckn());

        Customer customer = customerClient.findCustomerByTckn(tcknModel.getTckn()).getBody();

        LoanApplicationResult loanApplicationResult = loanApplicationClient.getLastLoanApplicationResultOfCustomerByTckn(tcknModel.getTckn()).getBody();

        smsClient.sendSms(loanApplicationResult);

        redirectAttributes.addAttribute("customerId", customer.getId());

        return "redirect:/customers/loanApplicationResultList";

    }

    @GetMapping("/applyForLoan")
    public String applyForLoan(@RequestParam(name = "customerId") UUID customerId, RedirectAttributes redirectAttributes) {

        Customer customer = customerClient.findCustomerById(customerId).getBody();

        loanApplicationClient.applyCustomerForLoan(customer.getTckn());

        LoanApplicationResult loanApplicationResult = loanApplicationClient.getLastLoanApplicationResultOfCustomerByTckn(customer.getTckn()).getBody();

        System.out.println("I'm gonna blow");

        smsClient.sendSms(loanApplicationResult);

        System.out.println("I blew up!");

        redirectAttributes.addAttribute("customerId", customerId);

        return "redirect:/customers/loanApplicationResultList";

    }

    @GetMapping("/loanApplicationResultInquiryForm")
    public String showLoanApplicationResultInquiryForm(Model model) {

        TcknModel tcknModel = new TcknModel();

        model.addAttribute("tcknModel", tcknModel);

        return "web/loan-application-result-inquiry-form";

    }

    @PostMapping("/loanApplicationResultInquiryForm")
    public String loanApplicationResult(@ModelAttribute("tcknModel") @Validated(OrderedChecks.class) TcknModel tcknModel, BindingResult result, RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            return "web/loan-application-result-inquiry-form";
        }

        Customer customer = customerClient.findCustomerByTckn(tcknModel.getTckn()).getBody();

        redirectAttributes.addAttribute("customerId", customer.getId());

        System.out.println("---->>>> " + customer.getTckn());

        return "redirect:/customers/loanApplicationResultList";


    }

    @GetMapping("/loanApplicationResultList")
    public String showLoanApplicationResultList(@RequestParam(name = "customerId") UUID customerId, Model model) {

        Customer customer = customerClient.findCustomerById(customerId).getBody();

        List<LoanApplicationResult> applicationResults = loanApplicationClient.getCustomerLoanApplicationResults(customer.getTckn()).getBody();

        model.addAttribute("applicationResults", applicationResults);
        model.addAttribute("customer", customer);

        return "web/loan-application-result-list";
    }

    @GetMapping("/delete")
    public String deleteCustomer(@RequestParam("customerId") UUID customerId) {

        customerClient.deleteCustomerById(customerId);

        return "redirect:/customers/list";

    }
}
