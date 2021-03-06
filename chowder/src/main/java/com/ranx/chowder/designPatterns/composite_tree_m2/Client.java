package com.ranx.chowder.designPatterns.composite_tree_m2;



import java.util.ArrayList;

/**
 * 场景类
 * @author ranx
 * @create 2019-04-21 9:19
 **/
public class Client {

    public static void main(String[] args) {
        Branch ceo = compositeCorpTree();
        //打印ceo信息
        System.out.println(ceo.getInfo());
        //打印所有员工信息
        System.out.println(getTreeInfo(ceo));
    }

    //组装整个树
    public static Branch compositeCorpTree() {
        //先组装一个组织结构
        Branch root = new Branch("赵大", "总经理", 100000);
        //产生树枝节点：三个部门经理
        Branch developDep = new Branch("钱二", "研发部门经理", 10000);
        Branch salesDep = new Branch("孙三", "销售部门经理", 20000);
        Branch financeDep = new Branch("李四", "财务部经理", 30000);
        //产生两个小组长
        Branch firstDevGroup = new Branch("周五", "开发一组组长", 5000);
        Branch secondDevGroup = new Branch("吴六", "开发二组组长", 6000);
        Leaf zhengqi = new Leaf("郑七", "研发部副总", 20000);
        //小职员
        Leaf a = new Leaf("a", "开发人员", 2000);
        Leaf b = new Leaf("b", "开发人员", 2000);
        Leaf c = new Leaf("c", "开发人员", 2000);
        Leaf d = new Leaf("d", "开发人员", 2000);
        Leaf e = new Leaf("e", "开发人员", 2000);
        Leaf f = new Leaf("f", "开发人员", 2000);
        Leaf g = new Leaf("g", "开发人员", 2000);
        Leaf h = new Leaf("h", "销售人员", 5000);
        Leaf i = new Leaf("i", "销售人员", 4000);
        Leaf j = new Leaf("j", "财务人员", 5000);
        Leaf k = new Leaf("k", "ceo秘书", 8000);

        //生产出各种人，下面进行组装
        //首先是定义总经理下有三个部门经理
        root.addSubordinate(developDep);
        root.addSubordinate(salesDep);
        root.addSubordinate(financeDep);
        root.addSubordinate(k);
        //定义研发部门下的结构
        developDep.addSubordinate(firstDevGroup);
        developDep.addSubordinate(secondDevGroup);
        developDep.addSubordinate(zhengqi);
        //开发小组
        firstDevGroup.addSubordinate(a);
        firstDevGroup.addSubordinate(b);
        firstDevGroup.addSubordinate(c);
        secondDevGroup.addSubordinate(d);
        secondDevGroup.addSubordinate(e);
        secondDevGroup.addSubordinate(f);
        //销售部
        salesDep.addSubordinate(h);
        salesDep.addSubordinate(i);
        //财务
        financeDep.addSubordinate(j);
        return root;
    }

    //遍历所有的树枝节点，打印出信息
    public static String getTreeInfo(Branch root) {
        ArrayList<Corp> subordinateList = root.getSuborfinate();
        String info = "";
        for (Corp s : subordinateList) {
            if (s instanceof  Leaf) { //是员工就直接获得信息
                info = info + s.getInfo() + "\n";
            } else { //是个小头目
                info = info + s.getInfo() + "\n" + getTreeInfo((Branch)s);
            }
        }
        return info;
    }
}
