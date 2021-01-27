import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class Main {

    public static void main(String[] args) {
        testTransfer();
    }

    public static void testTransfer() {
        int accountsCount = 9;
//        int threadsCount = 1000;
        int operationsCount = 100;

        Bank bank = new Bank();
        for (int i = 1; i <= accountsCount; i++) {
            bank.createAccount(Integer.toString(i), i * 10000);
        }
//        List<Thread> threadList = new ArrayList<>();

        ThreadPoolExecutor poolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(1000);
        bank.getAccounts().forEach((num, acc) -> System.out.println("Счет № " + acc.getAccNumber() + " баланс " + acc.getBalance()));
        poolExecutor.submit(() -> {
            for (int j = 0; j < operationsCount; j++) {
                //Рандомный аккаунт отправителя
                String fromAcc = Integer.toString(random(1, bank.getAccounts().size()));
                //Рандомный аккаунт получателя
                String toAcc = Integer.toString(random(1, bank.getAccounts().size()));
                //Транзакция на случайную сумму
                bank.transfer(fromAcc, toAcc, random(1000, 55000));
            }

        });
        poolExecutor.shutdown();


//        for (int i = 0; i < threadsCount; i++) {
//            Thread thread = new Thread(() -> {
//                for (int j = 0; j < operationsCount; j++) {
//                    //Рандомный аккаунт отправителя
//                    String fromAcc = Integer.toString(random(1, bank.getAccounts().size()));
//                    //Рандомный аккаунт получателя
//                    String toAcc = Integer.toString(random(1, bank.getAccounts().size()));
//                    //Транзакция на случайную сумму
//                    bank.transfer(fromAcc, toAcc, random(1000, 55000));
//                }
//            });
//            threadList.add(thread);
//        }

//        threadList.forEach(Thread::start);
//        threadList.forEach(t -> {
//            try {
//                t.join();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        });

    }

    private static int random(int min, int max) {
        max -= min;
        return (int) (Math.random() * ++max) + min;
    }
}