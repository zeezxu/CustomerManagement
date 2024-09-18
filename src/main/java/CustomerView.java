/**
 * @author zxu
 * @create 2023-07-20  2:15 PM
 */
public class CustomerView {
    private CustomerList customers = new CustomerList(10);

    public CustomerView() {
        Customer cust = new Customer("John Snow", "Male", 30, "248-123-4567",
                "JohnS@email.com");
        customers.addCustomer(cust);
    }

    public void enterMainMenu() {
        boolean loopFlag = true;
        do {
            System.out
                    .println("\n--------------------------Customer Management System-------------------------\n");
            System.out.println("                          1 Add New Customer");
            System.out.println("                          2 Edit Customer Information");
            System.out.println("                          3 Delete Customer");
            System.out.println("                          4 View Customers");
            System.out.println("                          5 Exit\n");
            System.out.print("                          Please select (1-5)：");

            char key = CMUtility.readMenuSelection();
            System.out.println();
            switch (key) {
                case '1':
                    addNewCustomer();
                    break;
                case '2':
                    modifyCustomer();
                    break;
                case '3':
                    deleteCustomer();
                    break;
                case '4':
                    listAllCustomers();
                    break;
                case '5':
                    System.out.print("Please confirm (Y/N)：");
                    char yn = CMUtility.readConfirmSelection();
                    if (yn == 'Y')
                        loopFlag = false;
                    break;
            }
        } while (loopFlag);
    }

    private void addNewCustomer() {
        System.out.println("------------------------Add New Customer------------------------");
        System.out.print("Full Name：");
        String name = CMUtility.readString(40);
        System.out.print("Gender：");
        String gender = CMUtility.readString(10);
        System.out.print("Age：");
        int age = CMUtility.readInt();
        System.out.print("Phone Number：");
        String phone = CMUtility.readString(15);
        System.out.print("Email Address：");
        String email = CMUtility.readString(30);

        Customer cust = new Customer(name, gender, age, phone, email);
        boolean flag = customers.addCustomer(cust);
        if (flag) {
            System.out
                    .println("------------------------Customer Successfully Added------------------------");
        } else {
            System.out.println("-------------------List is full. Cannot add more items.--------------------");
        }
    }

    private void modifyCustomer() {
        System.out.println("------------------------Edit Customer Information------------------------");

        int index = 0;
        Customer cust = null;
        for (;;) {
            System.out.print("Please choose a Customer ID (choose -1 to go back):");
            index = CMUtility.readInt();
            if (index == -1) {
                return;
            }

            cust = customers.getCustomer(index - 1);
            if (cust == null) {
                System.out.println("Customer not found.");
            } else
                break;
        }

        System.out.print("Full Name(" + cust.getName() + ")：");
        String name = CMUtility.readString(4, cust.getName());

        System.out.print("Gender(" + cust.getGender() + ")：");
        String gender = CMUtility.readString(10,cust.getGender());

        System.out.print("Age(" + cust.getAge() + ")：");
        int age = CMUtility.readInt(cust.getAge());

        System.out.print("Phone Number(" + cust.getPhone() + ")：");
        String phone = CMUtility.readString(15, cust.getPhone());

        System.out.print("Email Address(" + cust.getEmail() + ")：");
        String email = CMUtility.readString(15, cust.getEmail());

        cust = new Customer(name, gender, age, phone, email);

        boolean flag = customers.replaceCustomer(index - 1, cust);
        if (flag) {
            System.out
                    .println("------------------------Edit complete------------------------");
        } else {
            System.out.println("-------------Customer not found. Cannot edit.-----------------");
        }
    }

    private void deleteCustomer() {
        System.out.println("------------------------Delete Customer------------------------");

        int index = 0;
        Customer cust = null;
        for (;;) {
            System.out.print("Please choose a Customer ID (choose -1 to go back):");
            index = CMUtility.readInt();
            if (index == -1) {
                return;
            }

            cust = customers.getCustomer(index - 1);
            if (cust == null) {
                System.out.println("Customer not found.");
            } else
                break;
        }

        System.out.print("Please confirm (Y/N)：");
        char yn = CMUtility.readConfirmSelection();
        if (yn == 'N')
            return;

        boolean flag = customers.deleteCustomer(index - 1);
        if (flag) {
            System.out
                    .println("------------------------Delete complete------------------------");
        } else {
            System.out.println("-------------Customer not found. Cannot delete.-----------------");
        }
    }

    private void listAllCustomers() {
        System.out.println("--------------------------------Customer List--------------------------------");
        Customer[] custs = customers.getAllCustomers();
        if (custs.length == 0) {
            System.out.println("No record.");
        } else {
            System.out.println("Id\tFull Name\t\tGender\t\tAge\t\tPhone Number\t\tEmail Address");
            for (int i = 0; i < custs.length; i++) {
            System.out.println(i + 1 + "\t" + custs[i].getName() + "\t\t" + custs[i].getGender() + "\t\t" + custs[i].getAge() + "\t\t" + custs[i].getPhone() + "\t\t" + custs[i].getEmail());
//                System.out.println((i+1) + "\t" + custs[i].getDetails());
            }
        }

        System.out.println("-----------------------------------------------------------------------------");
    }

    public static void main(String[] args) {
        CustomerView cView = new CustomerView();
        cView.enterMainMenu();
    }
}
