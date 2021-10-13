package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalCustomers.CUSTOMER_ALICE;
import static seedu.address.testutil.TypicalEmployees.ALICE_EMPLOYEE;
import static seedu.address.testutil.TypicalEmployees.getTypicalAddressBookEmployees;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalReservation.ALICE_RESERVATION;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.person.Person;
import seedu.address.model.person.customer.Customer;
import seedu.address.model.person.employee.Employee;
import seedu.address.model.person.exceptions.DuplicateCustomerException;
import seedu.address.model.person.exceptions.DuplicateEmployeeException;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.model.person.supplier.Supplier;
import seedu.address.model.reservation.Reservation;
import seedu.address.testutil.CustomerBuilder;
import seedu.address.testutil.EmployeeBuilder;
import seedu.address.testutil.PersonBuilder;

public class AddressBookTest {

    private final AddressBook addressBook = new AddressBook();
    private final AddressBook addressBookEmployee = new AddressBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), addressBook.getPersonList());
        assertEquals(Collections.emptyList(), addressBookEmployee.getEmployeeList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyAddressBook_replacesData() {
        AddressBook newData = getTypicalAddressBook();
        addressBook.resetData(newData);
        assertEquals(newData, addressBook);
    }

    @Test
    public void resetData_withValidReadOnlyAddressBook_replacesDataEmployees() {
        AddressBook newData = getTypicalAddressBookEmployees();
        addressBookEmployee.resetData(newData);
        assertEquals(newData, addressBookEmployee);
    }

    @Test
    public void resetData_withDuplicatePersons_throwsDuplicatePersonException() {
        // Two persons with the same identity fields
        Person editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        List<Person> newPersons = Arrays.asList(ALICE, editedAlice);
        List<Employee> emptyList = new ArrayList<>();
        List<Customer> newCustomers = Arrays.asList();
        AddressBookStub newData = new AddressBookStub(newPersons, newCustomers, emptyList);


        assertThrows(DuplicatePersonException.class, () -> addressBook.resetData(newData));
    }

    @Test
    public void resetData_withDuplicateCustomers_throwsDuplicateCustomerException() {
        // Two customers with the same identity fields
        Customer editedAlice =
                new CustomerBuilder(CUSTOMER_ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        List<Customer> newCustomers = Arrays.asList(CUSTOMER_ALICE, editedAlice);
        List<Person> newPersons = Arrays.asList();
        List<Employee> emptyList = new ArrayList<>();
        AddressBookStub newData = new AddressBookStub(newPersons, newCustomers, emptyList);

        assertThrows(DuplicateCustomerException.class, () -> addressBook.resetData(newData));
    }

    @Test
    public void resetData_withDuplicateEmployees_throwsDuplicateEmployeeException() {
        // Two persons with the same identity fields
        Employee editedAlice = new EmployeeBuilder(ALICE_EMPLOYEE).withAddress(VALID_ADDRESS_BOB)
                .withTags(VALID_TAG_HUSBAND)
                .build();
        List<Employee> newEmployees = Arrays.asList(ALICE_EMPLOYEE, editedAlice);
        List<Person> emptyList = new ArrayList<>();

        List<Customer> emptyCustList = new ArrayList<>();
        AddressBookStub newData = new AddressBookStub(emptyList, emptyCustList, newEmployees);

        assertThrows(DuplicateEmployeeException.class, () -> addressBook.resetData(newData));
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasPerson(null));
    }

    @Test
    public void hasCustomer_nullCustomer_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasCustomer(null));
    }
    @Test
    public void hasEmployee_nullEmployee_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasEmployee(null));
    }

    @Test
    public void hasReservation_nullReservation_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasReservation(null));
    }

    @Test
    public void hasPerson_personNotInAddressBook_returnsFalse() {
        assertFalse(addressBook.hasPerson(ALICE));
    }

    @Test
    public void hasCustomer_customerNotInAddressBook_returnsFalse() {
        assertFalse(addressBook.hasCustomer(CUSTOMER_ALICE));
    }
    @Test
    public void hasEmployee_employeeNotInAddressBook_returnsFalse() {
        assertFalse(addressBookEmployee.hasEmployee(ALICE_EMPLOYEE));
    }

    @Test
    public void hasReservation_reservationNOtInAddressBook_returnFalse() {
        assertFalse(addressBook.hasReservation(ALICE_RESERVATION));
    }

    @Test
    public void hasPerson_personInAddressBook_returnsTrue() {
        addressBook.addPerson(ALICE);
        assertTrue(addressBook.hasPerson(ALICE));
    }

    @Test
    public void hasCustomer_customerInAddressBook_returnsTrue() {
        addressBook.addCustomer(CUSTOMER_ALICE);
        assertTrue(addressBook.hasCustomer(CUSTOMER_ALICE));
    }

    @Test
    public void hasEmployee_inAddressBook_returnsTrue() {
        addressBookEmployee.addEmployee(ALICE_EMPLOYEE);
        assertTrue(addressBookEmployee.hasEmployee(ALICE_EMPLOYEE));
    }

    @Test
    public void hasReservation_reservationInAddressBook_returnsTrue() {
        addressBook.addReservation(ALICE_RESERVATION);
        assertTrue(addressBook.hasReservation(ALICE_RESERVATION));
    }

    @Test
    public void hasPerson_personWithSameIdentityFieldsInAddressBook_returnsTrue() {
        addressBook.addPerson(ALICE);
        Person editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(addressBook.hasPerson(editedAlice));
    }

    @Test
    public void hasCustomer_customerWithSameIdentityFieldsInAddressBook_returnsTrue() {
        addressBook.addCustomer(CUSTOMER_ALICE);
        Customer editedAlice =
                new CustomerBuilder(CUSTOMER_ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(addressBook.hasCustomer(editedAlice));
    }

    @Test
    public void hasEmployee_employeeWithSameIdentityFieldsInAddressBook_returnsTrue() {
        addressBookEmployee.addEmployee(ALICE_EMPLOYEE);
        Employee editedAlice = new EmployeeBuilder(ALICE_EMPLOYEE)
                .withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(addressBookEmployee.hasEmployee(editedAlice));
    }

    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> addressBook.getPersonList().remove(0));
    }

    @Test
    public void getCustomerList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> addressBook.getCustomerList().remove(0));
    }

    @Test
    public void getEmployeeList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> addressBookEmployee.getEmployeeList().remove(0));
    }

    @Test
    public void getReservationList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> addressBook.getReservationList().remove(0));
    }

    /**
     * A stub ReadOnlyAddressBook whose persons list can violate interface constraints.
     */
    private static class AddressBookStub implements ReadOnlyAddressBook {

        private final ObservableList<Person> persons = FXCollections.observableArrayList();
        private final ObservableList<Customer> customers = FXCollections.observableArrayList();
        private final ObservableList<Employee> employees = FXCollections.observableArrayList();
        private final ObservableList<Supplier> suppliers = FXCollections.observableArrayList();

        AddressBookStub(Collection<Person> persons, Collection<Customer> customers,
                        Collection<Employee> employees) {
            this.persons.setAll(persons);
            this.employees.setAll(employees);
            this.customers.setAll(customers);
        }

        @Override
        public ObservableList<Person> getPersonList() {
            return persons;
        }

        @Override
        public ObservableList<Customer> getCustomerList() {
            return customers;
        }

        @Override
        public ObservableList<Employee> getEmployeeList() {
            return employees;
        }

        @Override
        public ObservableList<Supplier> getSupplierList() {
            return suppliers;
        }

        @Override
        public ObservableList<Reservation> getReservationList() {
            return null;
        }
    }

}
