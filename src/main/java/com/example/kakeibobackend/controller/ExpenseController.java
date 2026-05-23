package com.example.kakeibobackend.controller;

import com.example.kakeibobackend.entity.Expense;
import com.example.kakeibobackend.repository.ExpenseRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/expenses")
@CrossOrigin("*")
public class ExpenseController {

    @Autowired
    private ExpenseRepository expenseRepository;

    @PostMapping
    public Expense addExpense(@RequestBody Expense expense) {

        return expenseRepository.save(expense);
    }
    @DeleteMapping("/{id}")
    public String deleteExpense(@PathVariable Long id) {

        expenseRepository.deleteById(id);

        return "Expense Deleted Successfully";
    }
    @GetMapping("/")
    public List<Expense> getAllExpenses() {

        return expenseRepository.findAll();
    }
    @GetMapping("/{id}")
    public Expense getExpenseById(@PathVariable Long id) {

        return expenseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Expense not found"));
    }
    @PutMapping("/{id}")
    public Expense updateExpense(
            @PathVariable Long id,
            @RequestBody Expense updatedExpense) {

        Expense expense = expenseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Expense not found"));

        expense.setTitle(updatedExpense.getTitle());
        expense.setAmount(updatedExpense.getAmount());
        expense.setCategory(updatedExpense.getCategory());
        expense.setDate(updatedExpense.getDate());

        return expenseRepository.save(expense);
    }
}