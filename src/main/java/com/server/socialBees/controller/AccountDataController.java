package com.server.socialBees.controller;

import com.server.socialBees.dto.UserDTO;
import com.server.socialBees.entity.AccountData;
import com.server.socialBees.entity.User;
import com.server.socialBees.repository.UserRepository;
import com.server.socialBees.service.AccountDataService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/accountData")
public class AccountDataController {
    @Autowired
    private AccountDataService accountDataService;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    @PostMapping()
    public ResponseEntity<String> createAccountData(@RequestBody UserDTO userDTO){
        User user = userRepository.findUserById(userDTO.getId());
        AccountData accountData = new AccountData();
        accountData.setAccountNumber(userDTO.getAccountData());
        accountData.setDeleted(false);
        accountData.setUser(user);

        AccountData createdAccountData = accountDataService.createAccountData(accountData);
        return new ResponseEntity<>("Account data added successfully!", HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountData> getAccountData(@PathVariable Integer id) {
        AccountData accountData = accountDataService.getAccountDataBy(id);
        if(accountData == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(accountData, HttpStatus.OK);
        }
    }

    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity<String> updateAccountData(@RequestBody UserDTO userDTO, @PathVariable Integer id){
        AccountData updatedAccountData = new AccountData();
        updatedAccountData.setId(id);
        updatedAccountData.setAccountNumber(userDTO.getAccountData());

        accountDataService.updateAccountData(updatedAccountData);

        return new ResponseEntity<>("Account data updated successfully!", HttpStatus.OK);
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAccountData(@PathVariable Integer id){
        accountDataService.deleteAccountDataBy(id);

        return new ResponseEntity<>("Account data deleted successfully!", HttpStatus.OK);
    }
}
