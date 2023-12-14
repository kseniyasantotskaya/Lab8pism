package operations;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
public class StringListWrapper {

    private List<String> strings;

    public StringListWrapper() {
        // Пустой конструктор для JAXB
    }

    public StringListWrapper(List<String> strings) {
        this.strings = strings;
    }

    @XmlElement
    public List<String> getStrings() {
        return strings;
    }

    public void setStrings(List<String> strings) {
        this.strings = strings;
    }
}