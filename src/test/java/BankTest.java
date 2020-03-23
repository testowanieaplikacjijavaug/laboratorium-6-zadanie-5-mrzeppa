import org.assertj.swing.core.matcher.JButtonMatcher;
import org.assertj.swing.edt.FailOnThreadViolationRepaintManager;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.timing.Pause;
import org.junit.BeforeClass;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class BankTest {
    private static final JButtonMatcher B1 = JButtonMatcher.withText("Wplac 50");
    private static final JButtonMatcher B2 = JButtonMatcher.withText("Wyplac 50");

    private Bank bank;

    private FrameFixture window;

    @BeforeClass
    public static void setupOnce(){
        FailOnThreadViolationRepaintManager.install();
    }

    @BeforeEach
    public void setUp() {
        bank = GuiActionRunner.execute(Bank::new);
        window = new FrameFixture(bank);
        window.show();
    }

    @AfterEach
    public void tearDown() {
        window.cleanUp();
        bank = null;
    }

    @Test
    public void checkTitleTest() {
        window.requireTitle("Bank");
    }

    @Test
    public void afterClickButton1TextShouldBeChanged() {
        window.button(B1).click();
        Pause.pause();
        window.textBox().requireText("150");
    }

    @Test
    public void afterClickingButton1ManyTimesTextShouldBeChanged() {
        for(int i = 0; i < 20; i++)
            window.button(B1).click();
        Pause.pause();
        window.textBox().requireText("1100");
    }

    @Test
    public void afterClickButton2TextShouldBeChanged() {
        window.button(B2).click();
        Pause.pause();
        window.textBox().requireText("50");
    }

    @Test
    public void whenValueIsZeroButtonShouldBeDisabled() {
        window.button(B2).click();
        Pause.pause();
        window.button(B2).click();
        Pause.pause();
        window.textBox().requireText("0");
        window.button(B2).requireDisabled();
    }

    @Test
    public void afterStartMoneyShouldBe100() {
        window.textBox().requireText("100");
    }

}