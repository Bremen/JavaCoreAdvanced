package AdditionalTask;

public class VowelCounterTest {
    public static void main(String[] args) {

        //Строка с входными данными из условия к ДЗ
        String input = "bc yfammacoqlwgjj yrtstrcw  hujqgqvqqjcrsdhdb" + "\n" +
        "quajwsdnubbcpfrgqjc  sqxlo vcbqadqj j vloetrk e uhnc f s" + "\n" +
        "pptgqyiy mzceoha x zeq  z y  m icpjzv ec elg ag" + "\n" +
        "xzaip wpoivhpqmx uxcpulvbibhe ju jydwizx" + "\n" +
        "v wmzvao cqwtmezt ihi u ggkkgjqbvnttktwfe ba" + "\n" +
        "auoekyf sqzdbfsz n jkef jjorkcelf pvgajyrhk" + "\n" +
        "cxhxlwhpbvyrxwb uslch pjvv fgyyne  qku rxmjvkrimlnvauljz pd" + "\n" +
        "vkjoiur eygirvab itesqytbn pfekbnzcroog  rdz dbbhu  smoob" + "\n" +
        "rmabtp ihcy k m g enjmqvskjtlqqcdrlehsbvuhqmtc bklvzemvxuf" + "\n" +
        "nukxgcjkqbsmd dwomddivyiaszzvfsl djsmxdd uwlc hfsrnw tan" + "\n" +
        "a kg osqkmcjv qxkbbqqmkjb iuhsqhg  sc j yscugaovbcmzv" + "\n" +
        "hikmyxfw ri l to o ji jyirjqrf  hdsncempvq" + "\n" +
        "ishd c xkdinomf xya k usxnjtf bhyqrzamxkvuyxpkr psaymizkrh" + "\n" +
        "ut lofdofvzvrooqrmhfc gj jhdbwpdsdv nytaotw wzk" + "\n" +
        "mzat  davsfepahhffcakeomzzgwxwmkwmgiaqiwjhoejj t vtfa" + "\n" +
        "watdx bkfeiqci gavtoodlpeglarmwn szlm uxg nnduofzvgo xqgfb" + "\n" +
        "utdqjuqopxi fdczngozfwggefukpfwry jpdyqze  jevjs";

        //Создаем счетчик гласных и передаем в конструкторе входные данные
        VowelCounter counter = new VowelCounter(input);

        //вызываем метод подсчета гласных и смотрим в консоль на результат счета
        counter.count();
    }
}
