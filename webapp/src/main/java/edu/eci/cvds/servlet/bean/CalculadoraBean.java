package edu.eci.cvds.servlet.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ApplicationScoped;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.Arrays;

@ManagedBean(name= "CalculadoraBean")
@ApplicationScoped

public class CalculadoraBean {
    public ArrayList<Integer> nums;
    public String type;
    public double result;

    public CalculadoraBean(){
        nums = new ArrayList<Integer>();
    }
    public void calculate(){
        switch (type){
            case "Mean":
                this.setResult(calculateMean());
                break;
            case "StandartDeviation":
                this.setResult(calculateStandartDeviation());
                break;
            case "Mode":
                this.setResult(calculateMode());
                break;
            case "Variance":
                this.setResult(calculateVariance());
                break;
        }
    }

    public double calculateMean(){
        int sum=0;
        for (int num:nums) {
            sum+=num;
        }
        double res = (double) sum/nums.size();
        return res;
    }

    public double calculateStandartDeviation(){
        double variance = calculateVariance();
        double standartDesviation = Math.sqrt(variance);
        return  standartDesviation;
    }

    public double calculateVariance(){
        double mean = calculateMean();
        double sum=0;
        for (int num:nums) {
            sum += Math.pow(num-mean,2f);
        }
        double res = sum/nums.size();

        return res;
    }

    public double calculateMode(){
        int valueAppear1;
        int valueAppear2=0;
        int mode=0;
        boolean manyModes=false;
        for (int num:this.nums) {
            valueAppear1=0;
            for (int i = 0; i < nums.size(); i++) {
                if (num==nums.get(i)){
                    valueAppear1++;
                }
            }
            if (valueAppear1>valueAppear2){
                mode=num;
                valueAppear2=valueAppear1;
            }else if (valueAppear1==valueAppear2){
                manyModes=true;
            }
        }
        if (!manyModes){
            mode=0;
        }
        return mode;
    }

    public void restart(){
        nums.clear();
        System.out.println(this.nums);
    }

    public ArrayList<Integer> getNums() {
        return nums;
    }

    public void setNums(String nums) {
        ArrayList<String> lista = new ArrayList<>(Arrays.asList(nums.split(";")));
        for (String num: lista) {
            this.nums.add(Integer.parseInt(num));
        }
        System.out.println(this.nums);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }
}

