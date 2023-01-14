module se.kth.martsten.lab_4_v2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.swing;

    opens se.kth.martsten.lab_4_v2 to javafx.fxml;
    exports se.kth.martsten.lab_4_v2;
}