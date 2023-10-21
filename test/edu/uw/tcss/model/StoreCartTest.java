package edu.uw.tcss.model;

import edu.uw.tcss.model.Item;
import edu.uw.tcss.model.StoreItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtensionContext;
import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.jupiter.api.Assertions.*;

public class StoreCartTest {

    /**
     * The name of the item used in testing.
     */
    private static final String ITEM_NAME = "Item";

    /**
     * The name of the bulk item used in testing.
     */
    private static final String BULK_ITEM_NAME = "Bulk Item";

    /**
     * The price of the item used in testing.
     */
    private static final String ITEM_PRICE = ".50";

    /**
     * The bulk price of the item used in testing.
     */
    private static final String BULK_PRICE = "4.00";

    /**
     * The negative price of the item used in testing.
     */
    private static final String NEGATIVE_PRICE = "-0.01";

    /**
     * The bulk quantity of the item used in testing.
     */
    private static final int BULK_QUANTITY = 10;

    /**
     * The quantity of the item order used in testing.
     */
    private static final int TEST_QUANTITY = 10;

    /**
     * An object of class Item to use in testing.
     */

    private static final Item TEST_ITEM = new StoreItem(ITEM_NAME, new BigDecimal(ITEM_PRICE));

    /**
     * An object of class Item to use in testing.
     */
    private static final Item TEST_BULK_ITEM =
            new StoreItem(BULK_ITEM_NAME, new BigDecimal(ITEM_PRICE),
                    BULK_QUANTITY, new BigDecimal(BULK_PRICE));

    /**
     * An object of class StoreItemOrder to use in testing
     */
    private static final StoreItemOrder TEST_STORE_ITEM_ORDER = new StoreItemOrder(TEST_ITEM,
            TEST_QUANTITY);

    /**
     *A Storecart object to use in testing.
     */
    private static StoreCart TEST_CART = new StoreCart();

    /**
     *This method "resets" the cart for testing.
     */
    @BeforeEach
    final void setUp() {
        TEST_CART = new StoreCart();
    }

    @Test
    public final void testCartConstructor() {
        assertAll("Test the initalization of a client cart.",
                () -> {
                    setUp();
                    assertEquals(0, TEST_CART.getCartSize(), "Instantiated cart should have 0 StoreItem Objects.");
                },
                () -> {
                    setUp();
                    BigDecimal testprice = new BigDecimal(6).setScale(2, RoundingMode.HALF_EVEN);
                    StoreItem testeritem3 = new StoreItem("n2", new BigDecimal(1), 6, new BigDecimal(3.00));
                    StoreItemOrder testeritemorder3 = new StoreItemOrder(testeritem3, 6);
                    TEST_CART.add(testeritemorder3);
                    assertEquals(testprice, TEST_CART.calculateTotal(), "Instantiated cart should have a false membership. Thus the client should pay the full price ($6.00 instead of $3.00)");
                });
    }

    @Test
    public final void testCartAdd() {
        assertAll("Test the add method of a cart",
                () -> {
                    setUp();
                    TEST_CART.add(TEST_STORE_ITEM_ORDER);
                    assertEquals(1, TEST_CART.getCartSize(), "One StoreItemOrder object should have been added to the cart.");
                },
                () -> {
                    setUp();
                    TEST_CART.add(new StoreItemOrder(new StoreItem("n1", new BigDecimal(3)), 1));
                    TEST_CART.add(new StoreItemOrder(new StoreItem("n2", new BigDecimal(3)), 1));
                    TEST_CART.add(new StoreItemOrder(new StoreItem("n3", new BigDecimal(3)), 1));
                    TEST_CART.add(new StoreItemOrder(new StoreItem("n4", new BigDecimal(3)), 1));
                    TEST_CART.add(new StoreItemOrder(new StoreItem("n5", new BigDecimal(3)), 1));
                    TEST_CART.add(new StoreItemOrder(new StoreItem("n6", new BigDecimal(3)), 1));
                    TEST_CART.add(new StoreItemOrder(new StoreItem("n7", new BigDecimal(3)), 1));
                    TEST_CART.add(new StoreItemOrder(new StoreItem("n8", new BigDecimal(3)), 1));
                    TEST_CART.add(new StoreItemOrder(new StoreItem("n9", new BigDecimal(3)), 1));
                    TEST_CART.add(new StoreItemOrder(new StoreItem("n10", new BigDecimal(3)), 1));
                    assertEquals(10, TEST_CART.getCartSize(), "There should be 10 storeitem objects in the cart.");
                },
                () -> {
                    setUp();
                    TEST_CART.add(new StoreItemOrder(new StoreItem("n1", new BigDecimal(3)), 3));
                    TEST_CART.add(new StoreItemOrder(new StoreItem("n1", new BigDecimal(3)), 5));
                    TEST_CART.add(new StoreItemOrder(new StoreItem("n2", new BigDecimal(3)), 2));
                    TEST_CART.add(new StoreItemOrder(new StoreItem("n2", new BigDecimal(3)), 6));

                    assertEquals(2, TEST_CART.getCartSize(), "Four StoreItem objects were added to" +
                            "the cart, but there are duplicates for each set of StoreItemOrder objects  so those should be overwritten " +
                            "resulting in 2 distinct storeItemOrder objects in the cart ");
                },
                () -> {
                    setUp();
                    TEST_CART.add(new StoreItemOrder(new StoreItem("n1", new BigDecimal(5)), 3));
                    TEST_CART.add(new StoreItemOrder(new StoreItem("n1", new BigDecimal(5)), 100));

                    assertEquals(new BigDecimal(500).setScale(2, RoundingMode.HALF_EVEN), TEST_CART.calculateTotal(), "One item with a quantity of 3 items was added. The same item with a quantity of 100 was added." +
                            "These items are the same, so the StoreItemOrder object with a quantity of 100, should return in a price of $500.00");
                });
    }

    @Test
    public final void testSetMembership() {
        assertAll("Test whether the membership can be toggled true or false",
                () -> {
                    setUp();
                    BigDecimal testprice = new BigDecimal(2).setScale(2, RoundingMode.HALF_EVEN);
                    TEST_CART.setMembership(true);
                    StoreItem testeritem2 = new StoreItem("n2", new BigDecimal(1), 6, new BigDecimal(2.00));
                    StoreItemOrder testeritemorder2 = new StoreItemOrder(testeritem2, 6);
                    TEST_CART.add(testeritemorder2);

                    assertEquals(testprice, TEST_CART.calculateTotal(), "If the memnbership is active, the total value should be $2.00");
                    },
                () -> {
                    setUp();
                    BigDecimal testprice = new BigDecimal(6).setScale(2, RoundingMode.HALF_EVEN);
                    TEST_CART.setMembership(false);
                    StoreItem testeritem3 = new StoreItem("n2", new BigDecimal(1), 6, new BigDecimal(2.00));
                    StoreItemOrder testeritemorder3 = new StoreItemOrder(testeritem3, 6);
                    TEST_CART.add(testeritemorder3);

                    assertEquals(testprice, TEST_CART.calculateTotal(), "Membership is set to false, so client should pay full price of $6.00");
                },
                () -> {
                    setUp();
                    BigDecimal testprice = new BigDecimal(6).setScale(2, RoundingMode.HALF_EVEN);
                    TEST_CART.setMembership(false);
                    TEST_CART.setMembership(true);
                    TEST_CART.setMembership(false);
                    StoreItem testeritem3 = new StoreItem("n2", new BigDecimal(1), 6, new BigDecimal(2.00));
                    StoreItemOrder testeritemorder3 = new StoreItemOrder(testeritem3, 6);
                    TEST_CART.add(testeritemorder3);

                    assertEquals(testprice, TEST_CART.calculateTotal(), "Membership is set to false, so client should pay full price of $6.00");
                },
                () -> {
                    setUp();
                    BigDecimal testprice = new BigDecimal(2).setScale(2, RoundingMode.HALF_EVEN);
                    TEST_CART.setMembership(true);
                    TEST_CART.setMembership(false);
                    TEST_CART.setMembership(true);
                    StoreItem testeritem2 = new StoreItem("n2", new BigDecimal(1), 6, new BigDecimal(2.00));
                    StoreItemOrder testeritemorder2 = new StoreItemOrder(testeritem2, 6);
                    TEST_CART.add(testeritemorder2);

                    assertEquals(testprice, TEST_CART.calculateTotal(), "If the membership is active, the total value should be $2.00");
                });
    }

    @Test
    public final void testCalculateTotal() {
        assertAll("Test whether the items' totals are calculated correctly",
                () -> {
                    setUp();
                    BigDecimal testprice = new BigDecimal(0).setScale(2, RoundingMode.HALF_EVEN);
                    assertEquals(testprice, TEST_CART.calculateTotal(), "The cart should be empty and your total should be 0 ($0.00).");
                },
                () -> {
                    setUp();
                    BigDecimal testprice = new BigDecimal(9.99).setScale(2, RoundingMode.HALF_EVEN);
                    StoreItem testeritem = new StoreItem("n1", new BigDecimal(9.99));
                    StoreItemOrder testeritemorder = new StoreItemOrder(testeritem, 1);
                    TEST_CART.add(testeritemorder);
                    assertEquals(testprice, TEST_CART.calculateTotal(), "The cart should have a single object with a total of 9.99 ($9.99).");
                },
                () -> {
                    setUp();
                    BigDecimal testprice = new BigDecimal(2).setScale(2, RoundingMode.HALF_EVEN);
                    TEST_CART.setMembership(true);
                    StoreItem testeritem2 = new StoreItem("n2", new BigDecimal(1), 6, new BigDecimal(2.00));
                    StoreItemOrder testeritemorder2 = new StoreItemOrder(testeritem2, 6);
                    TEST_CART.add(testeritemorder2);
                    assertEquals(testprice, TEST_CART.calculateTotal(), "There is a bulk quantity of 6 and the quantity of the item in the cart is 6." +
                            " The client also has his/her membership activated so the total should be 2.");
                },
                () -> {
                    setUp();
                    BigDecimal testprice = new BigDecimal(6).setScale(2, RoundingMode.HALF_EVEN);
                    TEST_CART.setMembership(false);
                    StoreItem testeritem3 = new StoreItem("n2", new BigDecimal(1), 6, new BigDecimal(2.00));
                    StoreItemOrder testeritemorder3 = new StoreItemOrder(testeritem3, 6);
                    TEST_CART.add(testeritemorder3);

                    assertEquals(testprice, TEST_CART.calculateTotal(), "There is a bulk quantity of 6 and the quantity of the item in the cart is 6. The client also does not have his/her membership activated.");
                },
                () -> {
                    setUp();
                    BigDecimal testprice = new BigDecimal(845.76).setScale(2, RoundingMode.HALF_EVEN);
                    TEST_CART.setMembership(true);
                    StoreItem testeritem1 = new StoreItem ("n1", new BigDecimal(4.41), 6, new BigDecimal(10.04));
                    StoreItem testeritem2 = new StoreItem("n2", new BigDecimal(399.00));
                    StoreItemOrder io1 = new StoreItemOrder(testeritem1, 22);
                    StoreItemOrder io2 = new StoreItemOrder(testeritem2, 2);

                    TEST_CART.add(io1);
                    TEST_CART.add(io2);
                    assertEquals(testprice, TEST_CART.calculateTotal(), "Membership is activated with one item having bulk quantity and one item not having a bulk quantity.");
                },
                () -> {
                    setUp();
                    BigDecimal testprice = new BigDecimal(895.02).setScale(2, RoundingMode.HALF_EVEN);
                    TEST_CART.setMembership(false);
                    StoreItem testeritem1 = new StoreItem ("n1", new BigDecimal(4.41), 6, new BigDecimal(10.04));
                    StoreItem testeritem2 = new StoreItem("n2", new BigDecimal(399.00));
                    StoreItemOrder io1 = new StoreItemOrder(testeritem1, 22);
                    StoreItemOrder io2 = new StoreItemOrder(testeritem2, 2);

                    TEST_CART.add(io1);
                    TEST_CART.add(io2);
                    assertEquals(testprice, TEST_CART.calculateTotal(), "Membership is activated with one item having bulk quantity and one item not having a bulk quantity.");
                });
    }

    @Test
    public final void testClear() {
        assertAll("Test whether the cart clears correctly",
                () -> {
                    setUp();
                    TEST_CART.clear();
                    TEST_CART.clear();
                    assertEquals(0, TEST_CART.getCartSize(), "Cart cleared twice should have empty cart.");
                },
                () -> {
                    setUp();
                    TEST_CART.add(new StoreItemOrder(new StoreItem("n1", new BigDecimal(3)),1));
                    TEST_CART.clear();
                    assertEquals(0, TEST_CART.getCartSize(), "Cart added one StoreItem object and cleared so it should be empty.");
                },
                () -> {
                    setUp();
                    TEST_CART.add(new StoreItemOrder(new StoreItem("n1", new BigDecimal(3)), 1));
                    TEST_CART.add(new StoreItemOrder(new StoreItem("n2", new BigDecimal(3)), 1));
                    TEST_CART.add(new StoreItemOrder(new StoreItem("n3", new BigDecimal(3)), 1));
                    TEST_CART.add(new StoreItemOrder(new StoreItem("n4", new BigDecimal(3)), 1));
                    TEST_CART.add(new StoreItemOrder(new StoreItem("n5", new BigDecimal(3)), 1));
                    TEST_CART.add(new StoreItemOrder(new StoreItem("n6", new BigDecimal(3)), 1));
                    TEST_CART.add(new StoreItemOrder(new StoreItem("n7", new BigDecimal(3)), 1));
                    TEST_CART.add(new StoreItemOrder(new StoreItem("n8", new BigDecimal(3)), 1));
                    TEST_CART.add(new StoreItemOrder(new StoreItem("n9", new BigDecimal(3)), 1));
                    TEST_CART.add(new StoreItemOrder(new StoreItem("n10", new BigDecimal(3)), 1));
                    TEST_CART.clear();
                    assertEquals(0, TEST_CART.getCartSize(), "Added 10 items and then cleared the cart; There should be 0 storeitem objects in the cart.");
                });
    }

    @Test
    public final void testGetCartSize() {
        assertAll("Test whether the cart is correctly retrieving the size",
                () -> {
                    setUp();
                    assertEquals(0, TEST_CART.getCartSize(), "Instantiated cart should be empty.");
                    },
                () -> {
                    setUp();
                    TEST_CART.add(new StoreItemOrder(new StoreItem("n1", new BigDecimal(3)),1));
                    assertEquals(1, TEST_CART.getCartSize(), "Cart added one StoreItem object so your cart should be size 1.");
                },
                () -> {
                    setUp();
                    TEST_CART.add(new StoreItemOrder(new StoreItem("n1", new BigDecimal(3)), 1));
                    TEST_CART.add(new StoreItemOrder(new StoreItem("n2", new BigDecimal(3)), 1));
                    TEST_CART.add(new StoreItemOrder(new StoreItem("n3", new BigDecimal(3)), 1));
                    TEST_CART.add(new StoreItemOrder(new StoreItem("n4", new BigDecimal(3)), 1));
                    TEST_CART.add(new StoreItemOrder(new StoreItem("n5", new BigDecimal(3)), 1));
                    TEST_CART.add(new StoreItemOrder(new StoreItem("n6", new BigDecimal(3)), 1));
                    TEST_CART.add(new StoreItemOrder(new StoreItem("n7", new BigDecimal(3)), 1));
                    TEST_CART.add(new StoreItemOrder(new StoreItem("n8", new BigDecimal(3)), 1));
                    TEST_CART.add(new StoreItemOrder(new StoreItem("n9", new BigDecimal(3)), 1));
                    TEST_CART.add(new StoreItemOrder(new StoreItem("n10", new BigDecimal(3)), 1));
                    assertEquals(10, TEST_CART.getCartSize(), "10 items were added:There should be 0 storeitem objects in the cart.");
                });
        }
    @Test
    void testToString() {
        setUp();
        StoreItemOrder it1 = new StoreItemOrder(new StoreItem("n1", new BigDecimal(3)), 1);
        StoreItemOrder it2 = new StoreItemOrder(new StoreItem("n2", new BigDecimal(3)), 1);

        TEST_CART.add(it1);
        TEST_CART.add(it2);

        assertEquals("[" + it1.toString() + " " + it2.toString() + "]", TEST_CART.toString(), "There should be the two objects in the cart" +
                "listed from hashcode and quantity.");
    }
}