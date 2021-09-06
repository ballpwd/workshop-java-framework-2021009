import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class HelloTest {

        @Test
        public void case01(){
                //Arrange
                Hello hello = new Hello();
                //Act
                String actualResult = hello.sayHi("puwadech") ;
                //Assert
                assertEquals("Hello, puwadech", actualResult);
        }

}
