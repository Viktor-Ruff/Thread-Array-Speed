public class TestArray {

    public static void main(String[] args) {

        CheckArraySpeed totalArray = new CheckArraySpeed();
        CheckArraySpeed halfArray = new CheckArraySpeed();

        totalArray.testTotalArray();
        halfArray.testHalfArray();


        //Вывод : Потоки ускоряют работу приложения! (при размере исходного массива 10000000, почти в 2 раза)
    }

}
