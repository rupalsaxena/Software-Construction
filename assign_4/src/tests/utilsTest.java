import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class utilsTest {
    @Test
    public void inputDecision() {

    }

    @Test
    public void validDecisionInput() {
        Boolean actual1 = utils.validDecisionInput("yes");
        Boolean expected1 = true;
        assertEquals(expected1, actual1);

        Boolean actual2 = utils.validDecisionInput("no");
        Boolean expected2 = true;
        assertEquals(expected2, actual2);

        Boolean actual3 = utils.validDecisionInput("No");
        Boolean expected3 = true;
        assertEquals(expected3, actual3);

        Boolean actual4 = utils.validDecisionInput("Yes");
        Boolean expected4 = true;
        assertEquals(expected4, actual4);

        Boolean actual5 = utils.validDecisionInput("random");
        Boolean expected5 = false;
        assertEquals(expected5, actual5);

        Boolean actual6 = utils.validDecisionInput("1233232");
        Boolean expected6 = false;
        assertEquals(expected6, actual6);

    }

    @Test
    public void inputBet() {

    }

    @Test
    public void validBetInput() {
        Player user = new Player("rupal", 100);
        Boolean actual1 = utils.validBetInput("10", user);
        Boolean expected1 = true;
        assertEquals(expected1, actual1);

        Boolean actual2 = utils.validBetInput("ncdkjsn", user);
        Boolean expected2 = false;
        assertEquals(expected2, actual2);

        Boolean actual3 = utils.validBetInput("1000", user);
        Boolean expected3 = false;
        assertEquals(expected3, actual3);


    }

    @Test
    public void inputCardDecision() {

    }

    @Test
    public void validCardDecisionInput() {
        Boolean actual1 = utils.validCardDecisionInput("hit");
        Boolean expected1 = true;
        assertEquals(expected1, actual1);

        Boolean actual2 = utils.validCardDecisionInput("Hit");
        Boolean expected2 = true;
        assertEquals(expected2, actual2);

        Boolean actual3 = utils.validCardDecisionInput("stay");
        Boolean expected3 = true;
        assertEquals(expected3, actual3);

        Boolean actual4 = utils.validCardDecisionInput("Stay");
        Boolean expected4 = true;
        assertEquals(expected4, actual4);

        Boolean actual5 = utils.validCardDecisionInput("random");
        Boolean expected5 = false;
        assertEquals(expected5, actual5);
    }

    @Test
    public void testinputName() {
//        Scanner scan = new Scanner(System.in);
//        InputStream sysInBackup = System.in;
        //System.setIn(new ByteArrayInputStream("JohnDoe\n".getBytes()));
        //assertEquals("JohnDoe", utils.inputName());
//        System.setIn(sysInBackup);
    }

    //@Test (expected = IllegalArgumentException.class)
    //public void testIllegalArgumentException() {
//        InputStream sysInBackup = System.in;
        //System.setIn(new ByteArrayInputStream("JohnnyDoe\n".getBytes()));
        //utils.inputName();
//        System.setIn(sysInBackup);
    //}

    @Test
    public void testcardValues() {

    }

    @Test
    public void testinitialCards() {
        ArrayList<Card> actual = utils.initialCards(Deck.getInstance());
        for (int i=0; i<actual.size(); i++) {
            Card testCard = actual.get(i);
            assertTrue(testCard instanceof Card);
        }
    }

    // check how to test methods which do not return anything
}