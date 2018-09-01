package Ch4_FundamentalAlgorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Comparable_Comparator {
    public static void main (String[] args) throws java.lang.Exception
    {
        List<Student> list = new ArrayList<Student>();
        
        list.add(new Student("a", 5));
        list.add(new Student("b", 10));
        list.add(new Student("c", 1));
        list.add(new Student("d", 52));
        list.add(new Student("e", 23));
        
        Collections.sort(list);
        
        for (Student s : list) {
            System.out.println(s.getScore());
        }
        /*
         * °á°ú
         * 1
         * 5
         * 10
         * 23
         * 52
         */
    }
}
 
class Student implements Comparable<Student> {
    String name;
    int score;
    
    public Student(String name, int score) {
        this.name = name;
        this.score = score;
    }
    
    public String getName() {
        return this.name;
    }
    
    public int getScore() {
        return this.score;
    }
    
    @Override
    public int compareTo(Student s) {
        if (this.score < s.getScore()) {
            return -1;
        } else if (this.score > s.getScore()) {
            return 1;
        }
        return 0;
    }
}
