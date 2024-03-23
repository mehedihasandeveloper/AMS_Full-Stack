package com.accountingManagementSystem.serviceBusiness.Services;

import com.accountingManagementSystem.serviceBusiness.Models.*;
import com.accountingManagementSystem.serviceBusiness.Repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {
    @Value("${file.path}")
    private String FOLDER_PATH;
    @Autowired
    private FileDataRepository fileDataRepository;
    @Autowired
    public TransactionRepo repo;
    @Autowired
    private CashRepo cashrepo;
    @Autowired
    private AccountsReceivableRepo receivablerepo;
    @Autowired
    private AccountsPayableRepo payableRepo;
    @Autowired
    private EquipmentRepo equipmentRepo;
    @Autowired
    private SuppliesRepo suppliesRepo;
    @Autowired
    private OwnerCapitalRepo capitalRepo;

    @Autowired
    private RevenueRepo revenueRepo;

    @Autowired
    private OwnerDrawingsRepo drawingsRepoRepo;

    @Autowired
    private ExpenseRepo expenseRepo;

    public Transaction addData(Transaction transactionP) {
        Transaction transaction = repo.save(transactionP);
        isCashContain(transaction);
        isReceivableContain(transaction);
        isEquipmentContain(transaction);
        isSuppliesContain(transaction);
        isPayableContain(transaction);
        isOwnerCapitalContain(transaction);
        isDrawingsContain(transaction);
        isRevenueContain(transaction);
        isExpenseContain(transaction);
        return transaction;
    }

    public Transaction updateData(Transaction transaction) {
        isCashUpdated(transaction);
        isReceivableUpdated(transaction);
        isEquipmentUpdated(transaction);
        isSuppliesUpdated(transaction);
        isPayableUpdated(transaction);
        isOwnerCapitalUpdated(transaction);
        isDrawingsUpdated(transaction);
        isRevenueUpdated(transaction);
        isExpenseUpdated(transaction);
        return repo.save(transaction);
    }

    public void deleteData(Long id) {
        OwnerCapital capital = capitalRepo.getUpdateData(id);
        Revenue revenue = revenueRepo.getUpdateData(id);
        Cash cash = cashrepo.getUpdateData(id);
        Equipment equipment = equipmentRepo.getUpdateData(id);
        Supplies supplies = suppliesRepo.getUpdateData(id);
        AccountsPayable payable = payableRepo.getUpdateData(id);
        OwnerDrawings drawings = drawingsRepoRepo.getUpdateData(id);
        Expense expense = expenseRepo.getUpdateData(id);
        AccountReceivable receivable = receivablerepo.getUpdateData(id);

        FileData fileData = fileDataRepository.getUpdateData(id);
        if (revenue != null) {
            revenueRepo.delete(revenue);
        }
        if (capital != null) {
            capitalRepo.delete(capital);
        }
        if (cash != null) {
            cashrepo.delete(cash);
        }
        if (equipment != null) {
            equipmentRepo.delete(equipment);
        }
        if (supplies != null) {
            suppliesRepo.delete(supplies);
        }
        if (payable != null) {
            payableRepo.delete(payable);
        }
        if (drawings != null) {
            drawingsRepoRepo.delete(drawings);
        }
        if (expense != null) {
            expenseRepo.delete(expense);
        }
        if (receivable != null) {
            receivablerepo.delete(receivable);
        }

        if (fileData != null) {
            fileDataRepository.delete(fileData);
        }

        repo.deleteById(id);
    }

    private void isCashUpdated(Transaction transaction) {

        if (transaction.getDebitAccount().equals("cash")) {
            Cash updateCash = cashrepo.getUpdateData(transaction.getId());
            Cash lastCash = cashrepo.getLastData();
            Double oldBalance = updateCash.getBalance();
            Double newAmount = transaction.getAmount();
            Double oldAmount = updateCash.getDebitAmount();
            Double adjustedAmount = newAmount - oldAmount;
            updateCash.setEntryDate(transaction.getEntryDate());
            updateCash.setDebitAmount(transaction.getAmount());
            updateCash.setTransaction(transaction);
            updateCash.setDescription(transaction.getDescription());
            updateCash.setBalance(oldBalance + adjustedAmount);
            cashrepo.save(updateCash);
        } else if (transaction.getCreditAccount().equals("cash")) {
            Cash updateCash = cashrepo.getUpdateData(transaction.getId());
            Cash lastCash = cashrepo.getLastData();
            Double oldBalance = updateCash.getBalance();
            Double newAmount = transaction.getAmount();
            Double oldAmount = updateCash.getCreditAmount();
            Double adjustedAmount = newAmount - oldAmount;
            updateCash.setEntryDate(transaction.getEntryDate());
            updateCash.setCreditAmount(transaction.getAmount());
            updateCash.setTransaction(transaction);
            updateCash.setDescription(transaction.getDescription());

//            if (lastCash != null && lastCash.getBalance() < transaction.getAmount()) {
//                throw new RuntimeException("Illogical");
//            }
            updateCash.setBalance(oldBalance - adjustedAmount);
            cashrepo.save(updateCash);
        }
    }

    private void isReceivableUpdated(Transaction transaction) {

        if (transaction.getDebitAccount().equals("receivableaccount")) {
            AccountReceivable updateCash = receivablerepo.getUpdateData(transaction.getId());
            AccountReceivable lastCash = receivablerepo.getLastData();
            Double oldBalance = updateCash.getBalance();
            Double newAmount = transaction.getAmount();
            Double oldAmount = updateCash.getDebitAmount();
            Double adjustedAmount = newAmount - oldAmount;
            updateCash.setEntryDate(transaction.getEntryDate());

            updateCash.setDebitAmount(transaction.getAmount());
            updateCash.setTransaction(transaction);
            updateCash.setDescription(transaction.getDescription());
            updateCash.setBalance(oldBalance + adjustedAmount);
            receivablerepo.save(updateCash);
        } else if (transaction.getCreditAccount().equals("receivableaccount")) {
            AccountReceivable updateCash = receivablerepo.getUpdateData(transaction.getId());
            AccountReceivable lastCash = receivablerepo.getLastData();
            Double oldBalance = updateCash.getBalance();
            Double newAmount = transaction.getAmount();
            Double oldAmount = updateCash.getCreditAmount();
            Double adjustedAmount = newAmount - oldAmount;
            updateCash.setEntryDate(transaction.getEntryDate());
            updateCash.setCreditAmount(transaction.getAmount());
            updateCash.setTransaction(transaction);
            updateCash.setDescription(transaction.getDescription());

//            if (lastCash != null && lastCash.getBalance() < transaction.getAmount()) {
//                throw new RuntimeException("Illogical");
//            }
            updateCash.setBalance(oldBalance - adjustedAmount);
            receivablerepo.save(updateCash);
        }
    }

    private void isEquipmentUpdated(Transaction transaction) {

        if (transaction.getDebitAccount().equals("EquipmentAccount")) {
            Equipment updateCash = equipmentRepo.getUpdateData(transaction.getId());
            Equipment lastCash = equipmentRepo.getLastData();
            Double oldBalance = updateCash.getBalance();
            Double newAmount = transaction.getAmount();
            Double oldAmount = updateCash.getDebitAmount();
            Double adjustedAmount = newAmount - oldAmount;
            updateCash.setEntryDate(transaction.getEntryDate());

            updateCash.setDebitAmount(transaction.getAmount());
            updateCash.setTransaction(transaction);
            updateCash.setDescription(transaction.getDescription());
            updateCash.setBalance(oldBalance + adjustedAmount);
            equipmentRepo.save(updateCash);
        } else if (transaction.getCreditAccount().equals("EquipmentAccount")) {
            Equipment updateCash = equipmentRepo.getUpdateData(transaction.getId());
            Equipment lastCash = equipmentRepo.getLastData();
            Double oldBalance = updateCash.getBalance();
            Double newAmount = transaction.getAmount();
            Double oldAmount = updateCash.getCreditAmount();
            Double adjustedAmount = newAmount - oldAmount;
            updateCash.setEntryDate(transaction.getEntryDate());
            updateCash.setCreditAmount(transaction.getAmount());
            updateCash.setTransaction(transaction);
            updateCash.setDescription(transaction.getDescription());

//            if (lastCash != null && lastCash.getBalance() < transaction.getAmount()) {
//                throw new RuntimeException("Illogical");
//            }
            updateCash.setBalance(oldBalance - adjustedAmount);
            equipmentRepo.save(updateCash);
        }
    }

    private void isSuppliesUpdated(Transaction transaction) {
        if (transaction.getDebitAccount().equals("SuppliesAccount")) {
            Supplies updateCash = suppliesRepo.getUpdateData(transaction.getId());
            Supplies lastCash = suppliesRepo.getLastData();
            Double oldBalance = updateCash.getBalance();
            Double newAmount = transaction.getAmount();
            Double oldAmount = updateCash.getDebitAmount();
            Double adjustedAmount = newAmount - oldAmount;
            updateCash.setEntryDate(transaction.getEntryDate());

            updateCash.setDebitAmount(transaction.getAmount());
            updateCash.setTransaction(transaction);
            updateCash.setDescription(transaction.getDescription());
            updateCash.setBalance(oldBalance + adjustedAmount);
            suppliesRepo.save(updateCash);
        } else if (transaction.getCreditAccount().equals("SuppliesAccount")) {
            Supplies updateCash = suppliesRepo.getUpdateData(transaction.getId());
            Supplies lastCash = suppliesRepo.getLastData();
            Double oldBalance = updateCash.getBalance();
            Double newAmount = transaction.getAmount();
            Double oldAmount = updateCash.getCreditAmount();
            Double adjustedAmount = newAmount - oldAmount;
            updateCash.setEntryDate(transaction.getEntryDate());
            updateCash.setCreditAmount(transaction.getAmount());
            updateCash.setTransaction(transaction);
            updateCash.setDescription(transaction.getDescription());

//            if (lastCash != null && lastCash.getBalance() < transaction.getAmount()) {
//                throw new RuntimeException("Illogical");
//            }
            updateCash.setBalance(oldBalance - adjustedAmount);
            suppliesRepo.save(updateCash);
        }
    }

    private void isPayableUpdated(Transaction transaction) {
        if (transaction.getDebitAccount().equals("PayableAccount")) {
            AccountsPayable updateCash = payableRepo.getUpdateData(transaction.getId());
            AccountsPayable lastCash = payableRepo.getLastData();
            Double oldBalance = updateCash.getBalance();
            Double newAmount = transaction.getAmount();
            Double oldAmount = updateCash.getDebitAmount();
            Double adjustedAmount = newAmount - oldAmount;
            updateCash.setEntryDate(transaction.getEntryDate());

            updateCash.setDebitAmount(transaction.getAmount());
            updateCash.setTransaction(transaction);
            updateCash.setDescription(transaction.getDescription());
            updateCash.setBalance(oldBalance - adjustedAmount);
            payableRepo.save(updateCash);
        } else if (transaction.getCreditAccount().equals("PayableAccount")) {
            AccountsPayable updateCash = payableRepo.getUpdateData(transaction.getId());
            AccountsPayable lastCash = payableRepo.getLastData();
            Double oldBalance = updateCash.getBalance();
            Double newAmount = transaction.getAmount();
            Double oldAmount = updateCash.getCreditAmount();
            Double adjustedAmount = newAmount - oldAmount;
            updateCash.setEntryDate(transaction.getEntryDate());
            updateCash.setCreditAmount(transaction.getAmount());
            updateCash.setTransaction(transaction);
            updateCash.setDescription(transaction.getDescription());

//            if (lastCash != null && lastCash.getBalance() < transaction.getAmount()) {
//                throw new RuntimeException("Illogical");
//            }
            updateCash.setBalance(oldBalance + adjustedAmount);
            payableRepo.save(updateCash);
        }
    }

    private void isOwnerCapitalUpdated(Transaction transaction) {
        if (transaction.getDebitAccount().equals("OwnerCapital")) {
            throw new RuntimeException("Illogical");
        } else if (transaction.getCreditAccount().equals("OwnerCapital")) {
            OwnerCapital updateCash = capitalRepo.getUpdateData(transaction.getId());
            OwnerCapital lastCash = capitalRepo.getLastData();
            Double oldBalance = updateCash.getBalance();
            Double newAmount = transaction.getAmount();
            Double oldAmount = updateCash.getCreditAmount();
            Double adjustedAmount = newAmount - oldAmount;
            updateCash.setEntryDate(transaction.getEntryDate());
            updateCash.setCreditAmount(transaction.getAmount());
            updateCash.setTransaction(transaction);
            updateCash.setDescription(transaction.getDescription());

//            if (lastCash != null && lastCash.getBalance() < transaction.getAmount()) {
//                throw new RuntimeException("Illogical");
//            }
            updateCash.setBalance(oldBalance + adjustedAmount);
            capitalRepo.save(updateCash);
        }
    }

    private void isDrawingsUpdated(Transaction transaction) {
        if (transaction.getDebitAccount().equals("OwnerDrawings")) {
            OwnerDrawings updateCash = drawingsRepoRepo.getUpdateData(transaction.getId());
            OwnerDrawings lastCash = drawingsRepoRepo.getLastData();
            Double oldBalance = updateCash.getBalance();
            Double newAmount = transaction.getAmount();
            Double oldAmount = updateCash.getDebitAmount();
            Double adjustedAmount = newAmount - oldAmount;
            updateCash.setEntryDate(transaction.getEntryDate());
            updateCash.setDebitAmount(transaction.getAmount());
            updateCash.setTransaction(transaction);
            updateCash.setDescription(transaction.getDescription());
            updateCash.setBalance(oldBalance + adjustedAmount);
            drawingsRepoRepo.save(updateCash);
        } else if (transaction.getCreditAccount().equals("OwnerDrawings")) {
            throw new RuntimeException("Owner Drawings can not be credited!");
        }
    }

    private void isRevenueUpdated(Transaction transaction) {
        if (transaction.getDebitAccount().equals("RevenueAccount")) {
            throw new RuntimeException("revenue can not be dedited!");
        } else if (transaction.getCreditAccount().equals("RevenueAccount")) {
            Revenue updateCash = revenueRepo.getUpdateData(transaction.getId());
            Revenue lastCash = revenueRepo.getLastData();
            Double oldBalance = updateCash.getBalance();
            Double newAmount = transaction.getAmount();
            Double oldAmount = updateCash.getCreditAmount();
            Double adjustedAmount = newAmount - oldAmount;
            updateCash.setEntryDate(transaction.getEntryDate());
            updateCash.setCreditAmount(transaction.getAmount());
            updateCash.setTransaction(transaction);
            updateCash.setDescription(transaction.getDescription());

//            if (lastCash != null && lastCash.getBalance() < transaction.getAmount()) {
//                throw new RuntimeException("Illogical");
//            }
            updateCash.setBalance(oldBalance + adjustedAmount);
            revenueRepo.save(updateCash);
        }
    }

    private void isExpenseUpdated(Transaction transaction) {
        if (transaction.getDebitAccount().equals("ExpenseAccount")) {
            Expense updateCash = expenseRepo.getUpdateData(transaction.getId());
            Expense lastCash = expenseRepo.getLastData();
            Double oldBalance = updateCash.getBalance();
            Double newAmount = transaction.getAmount();
            Double oldAmount = updateCash.getDebitAmount();
            Double adjustedAmount = newAmount - oldAmount;
            updateCash.setEntryDate(transaction.getEntryDate());
            updateCash.setDebitAmount(transaction.getAmount());
            updateCash.setTransaction(transaction);
            updateCash.setDescription(transaction.getDescription());
            updateCash.setBalance(oldBalance + adjustedAmount);
            expenseRepo.save(updateCash);
        } else if (transaction.getCreditAccount().equals("ExpenseAccount")) {
            throw new RuntimeException("expense can not be credited!");
        }
    }

    private void isExpenseContain(Transaction transaction) {
        Expense lastCash = expenseRepo.getLastData();

        if (transaction.getDebitAccount().equals("ExpenseAccount")) {
            Expense expense = new Expense();
            expense.setEntryDate(transaction.getEntryDate());
            expense.setDebitAmount(transaction.getAmount());
            expense.setTransaction(transaction);
            expense.setDescription(transaction.getDescription());
            expense.setBalance(transaction.getAmount() + (lastCash != null ? lastCash.getBalance() : 0));
            expenseRepo.save(expense);
        } else if (transaction.getCreditAccount().equals("ExpenseAccount")) {
            throw new RuntimeException("expense can not be credited!");

        }
    }

    private void isDrawingsContain(Transaction transaction) {
        OwnerDrawings lastCash = drawingsRepoRepo.getLastData();

        if (transaction.getDebitAccount().equals("OwnerDrawings")) {
            OwnerDrawings drawings = new OwnerDrawings();
            drawings.setEntryDate(transaction.getEntryDate());
            drawings.setDebitAmount(transaction.getAmount());
            drawings.setTransaction(transaction);
            drawings.setDescription(transaction.getDescription());
            drawings.setBalance(transaction.getAmount() + (lastCash != null ? lastCash.getBalance() : 0));
            drawingsRepoRepo.save(drawings);
        } else if (transaction.getCreditAccount().equals("OwnerDrawings")) {
            throw new RuntimeException("Illogical");
        }
    }

    private void isRevenueContain(Transaction transaction) {
        Revenue lastCash = revenueRepo.getLastData();
        if (transaction.getDebitAccount().equals("RevenueAccount")) {
            throw new RuntimeException("Revenue can not be debit");
        } else if (transaction.getCreditAccount().equals("RevenueAccount")) {
            Revenue revenue = new Revenue();
            revenue.setEntryDate(transaction.getEntryDate());
            revenue.setCreditAmount(transaction.getAmount());
            revenue.setTransaction(transaction);
            revenue.setDescription(transaction.getDescription());
            revenue.setBalance((lastCash != null ? lastCash.getBalance() : 0) + transaction.getAmount());
            revenueRepo.save(revenue);
        }
    }

    private void isOwnerCapitalContain(Transaction transaction) {
        OwnerCapital lastCash = capitalRepo.getLastData();
        if (transaction.getDebitAccount().equals("OwnerCapital")) {

            throw new RuntimeException("Illogical");

        } else if (transaction.getCreditAccount().equals("OwnerCapital")) {
            OwnerCapital capital = new OwnerCapital();
            capital.setEntryDate(transaction.getEntryDate());
            capital.setCreditAmount(transaction.getAmount());
            capital.setTransaction(transaction);
            capital.setDescription(transaction.getDescription());
            capital.setBalance((lastCash != null ? lastCash.getBalance() : 0) + transaction.getAmount());
            capitalRepo.save(capital);
        }
    }

    private void isSuppliesContain(Transaction transaction) {
        Supplies lastCash = suppliesRepo.getLastData();

        if (transaction.getDebitAccount().equals("SuppliesAccount")) {
            Supplies supplies = new Supplies();
            supplies.setEntryDate(transaction.getEntryDate());
            supplies.setDebitAmount(transaction.getAmount());
            supplies.setTransaction(transaction);
            supplies.setDescription(transaction.getDescription());
            supplies.setBalance(transaction.getAmount() + (lastCash != null ? lastCash.getBalance() : 0));
            suppliesRepo.save(supplies);
        } else if (transaction.getCreditAccount().equals("SuppliesAccount")) {
            Supplies supplies = new Supplies();
            supplies.setEntryDate(transaction.getEntryDate());
            supplies.setCreditAmount(transaction.getAmount());
            supplies.setTransaction(transaction);
            supplies.setDescription(transaction.getDescription());
//            if (lastCash != null && lastCash.getBalance() < transaction.getAmount()) {
//                throw new RuntimeException("Illogical");
//            }
            supplies.setBalance((lastCash != null ? lastCash.getBalance() : 0) - transaction.getAmount());
            suppliesRepo.save(supplies);
        }
    }

    private void isEquipmentContain(Transaction transaction) {
        Equipment lastCash = equipmentRepo.getLastData();

        if (transaction.getDebitAccount().equals("EquipmentAccount")) {
            Equipment equipment = new Equipment();
            equipment.setEntryDate(transaction.getEntryDate());
            equipment.setDebitAmount(transaction.getAmount());
            equipment.setTransaction(transaction);
            equipment.setDescription(transaction.getDescription());
            equipment.setBalance(transaction.getAmount() + (lastCash != null ? lastCash.getBalance() : 0));
            equipmentRepo.save(equipment);
        } else if (transaction.getCreditAccount().equals("EquipmentAccount")) {
            Equipment equipment = new Equipment();
            equipment.setEntryDate(transaction.getEntryDate());
            equipment.setCreditAmount(transaction.getAmount());
            equipment.setTransaction(transaction);
            equipment.setDescription(transaction.getDescription());

            if (lastCash != null && lastCash.getBalance() < transaction.getAmount()) {
                throw new RuntimeException("Illogical");
            }
            equipment.setBalance((lastCash != null ? lastCash.getBalance() : 0) - transaction.getAmount());
            equipmentRepo.save(equipment);
        }
    }

    private void isPayableContain(Transaction transaction) {
        AccountsPayable lastCash = payableRepo.getLastData();
        if (transaction.getDebitAccount().equals("PayableAccount")) {
            AccountsPayable payable = new AccountsPayable();
            payable.setEntryDate(transaction.getEntryDate());
            payable.setDebitAmount(transaction.getAmount());
            payable.setTransaction(transaction);
            payable.setDescription(transaction.getDescription());
            if (lastCash != null && lastCash.getBalance() < transaction.getAmount()) {
                throw new RuntimeException("Illogical");
            }
            payable.setBalance((lastCash != null ? lastCash.getBalance() : 0) - transaction.getAmount());
            payableRepo.save(payable);
        } else if (transaction.getCreditAccount().equals("PayableAccount")) {
            AccountsPayable payable = new AccountsPayable();
            payable.setEntryDate(transaction.getEntryDate());
            payable.setCreditAmount(transaction.getAmount());
            payable.setTransaction(transaction);
            payable.setDescription(transaction.getDescription());

            payable.setBalance((lastCash != null ? lastCash.getBalance() : 0) + transaction.getAmount());
            payableRepo.save(payable);
        }
    }

    private void isCashContain(Transaction transaction) {
        Cash lastCash = cashrepo.getLastData();
        Cash cash = new Cash();

        if (transaction.getDebitAccount().equals("cash")) {
            cash.setEntryDate(transaction.getEntryDate());
            cash.setDebitAmount(transaction.getAmount());
            cash.setTransaction(transaction);
            cash.setDescription(transaction.getDescription());
            cash.setBalance(transaction.getAmount() + (lastCash != null ? lastCash.getBalance() : 0));
            cashrepo.save(cash);
        } else if (transaction.getCreditAccount().equals("cash")) {
            cash.setEntryDate(transaction.getEntryDate());
            cash.setCreditAmount(transaction.getAmount());
            cash.setTransaction(transaction);
            cash.setDescription(transaction.getDescription());

            if (lastCash != null && lastCash.getBalance() < transaction.getAmount()) {
                throw new RuntimeException("Illogical");
            }
            cash.setBalance((lastCash != null ? lastCash.getBalance() : 0) - transaction.getAmount());
            cashrepo.save(cash);
        }
    }

    private void isReceivableContain(Transaction transaction) {
        AccountReceivable lastCash = receivablerepo.getLastData();
        AccountReceivable receivable = new AccountReceivable();
        if (transaction.getDebitAccount().equals("receivableaccount")) {
            receivable.setEntryDate(transaction.getEntryDate());
            receivable.setDebitAmount(transaction.getAmount());
            receivable.setTransaction(transaction);
            receivable.setDescription(transaction.getDescription());
            receivable.setBalance(transaction.getAmount() + (lastCash != null ? lastCash.getBalance() : 0));
            receivablerepo.save(receivable);
        } else if (transaction.getCreditAccount().equals("receivableaccount")) {
            receivable.setEntryDate(transaction.getEntryDate());
            receivable.setCreditAmount(transaction.getAmount());
            receivable.setTransaction(transaction);
            receivable.setDescription(transaction.getDescription());
            if (lastCash != null && lastCash.getBalance() < transaction.getAmount()) {
                throw new RuntimeException("Illogical");
            }
            receivable.setBalance((lastCash != null ? lastCash.getBalance() : 0) - transaction.getAmount());
            receivablerepo.save(receivable);
        }
    }

    public List<Transaction> getAllData() {
        return repo.findAll();
    }


    public Transaction getById(Long id) {
        return repo.findById(id).get();
    }


    public FileData updateFile(FileData fileData) {
        return fileDataRepository.save(fileData);
    }


    public FileData uploadImageToFileSystem(MultipartFile file, Long jId) throws IOException {
        String filePath = FOLDER_PATH + file.getOriginalFilename();
        Transaction transaction = new Transaction();
        transaction = repo.findById(jId).get();
        FileData fileData = fileDataRepository.save(
                FileData.builder()
                        .name(file.getOriginalFilename())
                        .type(file.getContentType())
                        .filePath(filePath)
                        .transaction(transaction).build()
        );
        file.transferTo(new File(filePath));

        if (fileData != null) {
            return fileData;
        }
        return null;
    }

    public byte[] downloadImageFromFileSystem(String fileName) throws IOException {
        FileData fileData = fileDataRepository.findAllSortedByNameUsingNative(fileName);
        String filePath = fileData.getFilePath();
        byte[] images = Files.readAllBytes(new File(filePath).toPath());
        return images;
    }


    public List<Transaction> search(Long id, LocalDate startDate, LocalDate endDate) {
        List<Transaction> transactions = new ArrayList<>();
        if (id != null && startDate != null && endDate != null) {
            return repo.findByIdAndEntryDateBetween(id, startDate, endDate);
        } else if (id != null) {
            Optional<Transaction> transaction = repo.findById(id);
            transaction.ifPresent(transactions::add);
            return transactions;
        } else if (startDate != null && endDate != null) {
            return repo.findByEntryDateBetween(startDate, endDate);
        }
        return repo.findAll();
    }
}



