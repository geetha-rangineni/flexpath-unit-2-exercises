import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExerciseTests {
    // TODO: Uncomment the following tests after you've implemented the Lion class

    @Test
    public void TestLionEat() {
        org.example.Lion lion = new org.example.Lion("Simba");
        assertEquals("Simba is eating.", lion.eat());
    }

    @Test
    public void TestLionSpeak() {
        org.example.Lion lion = new org.example.Lion("Simba");
        assertEquals("Simba lets out a loud ROAR!", lion.speak());
    }

    @Test
    public void TestLionSleep() {
        org.example.Lion lion = new org.example.Lion("Simba");
        assertEquals("Simba is sleeping. Simba dreams of a delicious steak.", lion.sleep());
    }


    // TODO: Uncomment the following tests after you've implemented the Elephant class

    @Test
    public void TestElephantEat() {
        org.example.Elephant elephant = new org.example.Elephant("Dumbo");
        assertEquals("Dumbo is eating.", elephant.eat());
    }

    @Test
    public void TestElephantSpeak() {
        org.example.Elephant elephant = new org.example.Elephant("Dumbo");
        assertEquals("Dumbo makes a loud trumpet sound.", elephant.speak());
    }

    @Test
    public void TestElephantSleep() {
        org.example.Elephant elephant = new org.example.Elephant("Dumbo");
        assertEquals("Dumbo is sleeping.", elephant.sleep());
    }


    // TODO: Uncomment the following tests after you've implemented the Parrot class

    @Test
    public void TestParrotEat() {
        org.example.Parrot parrot = new org.example.Parrot("Polly");
        assertEquals("Polly is eating a cracker.", parrot.eat());
    }

    @Test
    public void TestParrotSpeak() {
        org.example.Parrot parrot = new org.example.Parrot("Polly");
        assertEquals("Polly says 'Polly wants a cracker!'", parrot.speak());
    }

    @Test
    public void TestParrotSleep() {
        org.example.Parrot parrot = new org.example.Parrot("Polly");
        assertEquals("Polly is sleeping.", parrot.sleep());
    }

    @Test
    public void TestParrotFly() {
        org.example.Parrot parrot = new org.example.Parrot("Polly");
        assertEquals("Polly is flying around the zoo.", parrot.fly());
    }
}
