package com.xpanxion.solution;

import java.text.NumberFormat;
import java.util.*;
import java.util.stream.Collectors;
import com.xpanxion.java.assignments.DataAccess;
import com.xpanxion.java.assignments.model.Cat;
import com.xpanxion.java.assignments.model.PersonCat;

public class Worker {
    public void ex1 () {
        var products = DataAccess.getProducts();
        var departments = DataAccess.getDepartments();
        products.stream().forEach(product -> product.setDepartmentName(departments.stream().filter(department -> department.getId()==product.getDepartmentId()).map(department -> department.getName()).collect(Collectors.joining())));
        System.out.println(products);
    }
    public void ex2 () {
        var products = DataAccess.getProducts();
        products.stream().forEach(product -> product.setDepartmentName("N/A"));
        System.out.println(products);
    }
    public void ex3 () {
        var products = DataAccess.getProducts();
        var resList = products.stream().filter(product -> product.getDepartmentId()==1 && product.getPrice()>=10).collect(Collectors.toList());
        System.out.println(resList);
    }
    public void ex4 () {
        var products = DataAccess.getProducts();
        var totalPrice = products.stream().filter(product -> product.getDepartmentId()==2).mapToDouble(product -> product.getPrice()).sum();
        System.out.println(NumberFormat.getCurrencyInstance().format(totalPrice));
    }
    public void ex5 () {
        var people = DataAccess.getPeople();
        var resList = people.stream().filter(person -> person.getId()<=3).collect(Collectors.toList());
        resList.forEach(person -> person.setSsn(person.getSsn().substring(person.getSsn().length()-4,person.getSsn().length())));
        System.out.println(resList);
    }
    public void ex6 () {
        var cats = DataAccess.getCats();
//        Collections.sort(cats);
        var resList = cats.stream().sorted(Cat::compareTo).collect(Collectors.toList());
        System.out.println(resList);
    }
    public void ex7 () {
        HashMap<String,Integer> count = new HashMap<String,Integer>();
        var words = DataAccess.getWords();
        StringTokenizer st = new StringTokenizer(words);
        while(st.hasMoreTokens()) {
            String word = st.nextToken();
            if (count.containsKey(word)) count.put(word, count.get(word) + 1);
            else count.put(word, 1);
        }
        count.entrySet().stream().sorted(Map.Entry.<String,Integer>comparingByKey()).forEach(e -> System.out.println(e.getKey() + " " + e.getValue()));
    }
    public void ex8 () {
        var people = DataAccess.getPeople();
        people.forEach(person -> {person.setLastName(null);person.setAge(0);person.setSsn(null);});
        System.out.println(people);
    }
    public void ex9 () {
        var products = DataAccess.getProducts();
        products.stream().filter(product -> product.getDepartmentId()==1).forEach(product -> product.setPrice(product.getPrice()+(float)2));
        var totalPrice = products.stream().filter(product -> product.getDepartmentId()==1).mapToDouble(product -> product.getPrice()).sum();
        System.out.println(NumberFormat.getCurrencyInstance().format(totalPrice));
    }
    public void ex10 () {
        var cats = DataAccess.getCats();
        var people = DataAccess.getPeople();
        var peoplcat = people.stream().map(person -> new PersonCat(person.getId(),person.getFirstName(),cats.stream().filter(cat -> cat.getId()==person.getId()).collect(Collectors.toList()))).collect(Collectors.toList());
        System.out.println(peoplcat);
    }
}
