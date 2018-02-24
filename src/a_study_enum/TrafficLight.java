package a_study_enum;
//JDK1.6之前的switch语句只支持int,char,enum类型，使用枚举，能让我们的代码可读性更强。
//转自http://blog.csdn.net/qq_27093465/article/details/52180865
enum Signal {
    GREEN, YELLOW, RED
}
public class TrafficLight {
    Signal color = Signal.RED;
    public void change() {
        switch (color) {
            case RED:
                color = Signal.GREEN;
                System.out.println("color:"+color);
                break;
            case YELLOW:
                color = Signal.RED;
                break;
            case GREEN:
                color = Signal.YELLOW;
                break;
        }
    }
}

