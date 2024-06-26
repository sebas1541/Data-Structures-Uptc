package co.edu.uptc.models;

import co.edu.uptc.structures.MyQueue;
import co.edu.uptc.structures.MyStack;

public class Addit {
    private String numbers;

    public Addit(String numbers) {
        this.numbers = numbers;
    }

  public String add(){
      String[] nums = this.numbers.split("\s++");
      MyQueue<Integer> result = new MyQueue<>();
      for (String num : nums){
          MyStack<Integer> myStack = new MyStack<>();
          for (char c : num.toCharArray()){
              myStack.push(Character.getNumericValue(c));
          }
          MyQueue<Integer> newResult = new MyQueue<>();
          int carry = 0;
          while (!result.isEmpty() || !myStack.isEmpty() || carry != 0){
              int sum = carry;
              if (!myStack.isEmpty()){
                  sum += myStack.pop();
              }
              if (!result.isEmpty()){
                  sum += result.poll();
              }

              newResult.push(sum % 2);
              carry = sum / 2;
          }

          result = newResult;

      }

      StringBuilder sb = new StringBuilder();
      while (!result.isEmpty()){
          sb.insert(0, result.poll());
      }
      return sb.toString();
  }

}