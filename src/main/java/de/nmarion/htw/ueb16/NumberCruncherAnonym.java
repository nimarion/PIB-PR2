package de.nmarion.htw.ueb16;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class NumberCruncherAnonym {

  private float[] list;
  private final List<Operation> operations;

  public NumberCruncherAnonym(final float[] list) {
    this.list = list;
    operations = Arrays.asList(sum(), swirl(), divide(), subtract(), average());
  }

  public void crunch(String[] operations) {
    for (String s : operations) {
      for (Operation operator : this.operations) {
        if (operator.getName().equals(s.toLowerCase())) {
          operator.function();
        }
      }
    }
  }

  public float[] getNumbers() {
    return this.list;
  }

  private Operation sum() {
    return new Operation() {

      @Override
      public String getName() {
        return "sum";
      }

      @Override
      public void function() {
        for (int i = 0; i < list.length - 1; i++) {
          list[i + 1] += list[i];
        }
      }
    };
  }

  private Operation swirl() {
    return new Operation() {
      @Override
      public String getName() {
        return "swirl";
      }

      @Override
      public void function() {
        final Random r = new Random();
        for (int i = 0; i < list.length; i++) {
          list[r.nextInt(list.length - 1)] = list[r.nextInt(list.length - 1)];
        }
      }
    };
  }

  private Operation subtract() {
    return new Operation() {
      @Override
      public String getName() {
        return "subtract";
      }

      @Override
      public void function() {
        for (int i = 0; i < list.length - 1; i++) {
          list[i + 1] -= list[i];
        }
      }
    };
  }

  private Operation average() {
    return new Operation() {
      @Override
      public String getName() {
        return "average";
      }

      @Override
      public void function() {
        float sum = 0;
        int maxIndex = 0;
        float maxValue = 0;
        for (int i = 0; i < list.length; i++) {
          sum += list[i];
          if (list[i] > maxValue) {
            maxValue = list[i];
            maxIndex = i;
          }
        }
        float avg = sum / list.length;
        list[maxIndex] = avg;
      }
    };
  }

  private Operation divide() {
    return new Operation() {
      @Override
      public String getName() {
        return "divide";
      }

      @Override
      public void function() {
        Arrays.sort(list);
        for (int i = 0; i < list.length; i++) {
          list[list.length - 1 - i] /= list[i];
        }
      }
    };
  }

  public interface Operation {
    String getName();

    void function();
  }
}
