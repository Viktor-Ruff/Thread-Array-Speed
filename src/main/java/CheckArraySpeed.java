import java.util.Arrays;


public class CheckArraySpeed {


    private final int SIZE = 10000000;
    private final int HALF_SIZE = SIZE / 2;


    public void testTotalArray() {

        float[] array = new float[SIZE];
        Arrays.fill(array, 1f);

        long start = System.currentTimeMillis();

        for (int i = 0; i < array.length; i++) {
            array[i] = (float) (array[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) *
                    Math.cos(0.4f + i / 2));
        }

        long finish = System.currentTimeMillis();
        long workTime = finish - start;

        System.out.printf("Method testTotalArray worked: %d milliseconds  \n", workTime);

        //System.out.println(Arrays.toString(array));
    }

    public void testHalfArray() {

        float[] array = new float[SIZE];
        Arrays.fill(array, 1f);

        long start = System.currentTimeMillis();
        float[] firstHalfArray = Arrays.copyOfRange(array, 0, array.length / 2);
        float[] secondHalfArray = Arrays.copyOfRange(array, array.length / 2, array.length);

        Thread t1 = new Thread(() -> {

            for (int i = 0; i < firstHalfArray.length; i++) {
                firstHalfArray[i] = (float) (firstHalfArray[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) *
                        Math.cos(0.4f + i / 2));
            }
            //System.out.println(Thread.currentThread().getName() + ": Started");
        }, "Tread 1");


        Thread t2 = new Thread(() -> {

            for (int i = 0; i < secondHalfArray.length; i++) {
                secondHalfArray[i] = (float) (secondHalfArray[i] * Math.sin(0.2f + (i + HALF_SIZE) / 5) * Math.cos(0.2f + (i + HALF_SIZE) / 5) *
                        Math.cos(0.4f + (i + HALF_SIZE) / 2));
            }
            //System.out.println(Thread.currentThread().getName() + ": Started");
        }, "Tread 2");

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.arraycopy(firstHalfArray, 0, array, 0, firstHalfArray.length);
        System.arraycopy(secondHalfArray, 0, array, firstHalfArray.length, secondHalfArray.length);

        long finish = System.currentTimeMillis();
        long workTime = finish - start;

        System.out.printf("Method testHalfArray worked: %d milliseconds  \n", workTime);
        //System.out.println(Arrays.toString(array));
    }

}
