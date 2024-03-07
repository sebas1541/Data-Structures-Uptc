package co.edu.uptc.models;
import co.edu.uptc.structures.MyQueue;
import co.edu.uptc.structures.MyStack;

public class Addition {
    private String numbers;

    public Addition(String numbers) {
        this.numbers = numbers;
    }

    public String add() {
        String[] nums = this.numbers.split("\\s+");
        MyQueue<Integer> result = new MyQueue<>();

        for (String num : nums) {
            MyStack<Integer> stack = new MyStack<>();
            for (char c : num.toCharArray()) {
                stack.push(Character.getNumericValue(c));
            }

            MyQueue<Integer> newResult = new MyQueue<>();
            int countNext = 0;

            while (!stack.isEmpty() || !result.isEmpty() || countNext != 0) {
                int sum = countNext;

                if (!stack.isEmpty()) {
                    sum += stack.pop();
                }
                if (!result.isEmpty()) {
                    sum += result.poll();
                }

                newResult.push(sum % 10);
                countNext = sum / 10;
            }
            result = newResult;
        }
        StringBuilder finalResult = new StringBuilder();
        while (!result.isEmpty()) {
            finalResult.insert(0, result.poll());
        }
        return finalResult.toString();
    }
}