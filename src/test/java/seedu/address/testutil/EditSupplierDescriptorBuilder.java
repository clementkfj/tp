package seedu.address.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.EditSupplierCommand.EditSupplierDescriptor;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.supplier.DeliveryDetails;
import seedu.address.model.person.supplier.Supplier;
import seedu.address.model.person.supplier.SupplyType;
import seedu.address.model.tag.Tag;

/**
 * A utility class to help with building EditSupplierDescriptor objects.
 */
public class EditSupplierDescriptorBuilder {

    private EditSupplierDescriptor descriptor;

    public EditSupplierDescriptorBuilder() {
        descriptor = new EditSupplierDescriptor();
    }

    public EditSupplierDescriptorBuilder(EditSupplierDescriptor descriptor) {
        this.descriptor = new EditSupplierDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditSupplierDescriptor} with fields containing {@code supplier}'s details
     */
    public EditSupplierDescriptorBuilder(Supplier supplier) {
        descriptor = new EditSupplierDescriptor();
        descriptor.setName(supplier.getName());
        descriptor.setPhone(supplier.getPhone());
        descriptor.setEmail(supplier.getEmail());
        descriptor.setAddress(supplier.getAddress());
        descriptor.setTags(supplier.getTags());
        descriptor.setSupplyType(supplier.getSupplyType());
        descriptor.setDeliveryDetails(supplier.getDeliveryDetails());
    }

    /**
     * Sets the {@code Name} of the {@code EditSupplierDescriptor} that we are building.
     */
    public EditSupplierDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code EditSupplierDescriptor} that we are building.
     */
    public EditSupplierDescriptorBuilder withPhone(String phone) {
        descriptor.setPhone(new Phone(phone));
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code EditSupplierDescriptor} that we are building.
     */
    public EditSupplierDescriptorBuilder withEmail(String email) {
        descriptor.setEmail(new Email(email));
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code EditSupplierDescriptor} that we are building.
     */
    public EditSupplierDescriptorBuilder withAddress(String address) {
        descriptor.setAddress(new Address(address));
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditPersonDescriptor}
     * that we are building.
     */
    public EditSupplierDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    /**
     * Sets the {@code SupplyType} of the {@code EditSupplierDescriptor} that we are building.
     */
    public EditSupplierDescriptorBuilder withSupplyType(String supplyType) {
        descriptor.setSupplyType(new SupplyType(supplyType));
        return this;
    }

    /**
     * Sets the {@code DeliveryDetails} of the {@code EditSupplierDescriptor} that we are building.
     */
    public EditSupplierDescriptorBuilder withDeliveryDetails(String deliveryDetails) {
        descriptor.setDeliveryDetails(new DeliveryDetails(deliveryDetails));
        return this;
    }

    public EditSupplierDescriptor build() {
        return descriptor;
    }
}
