package il.client;

import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.dialogs.MFXGenericDialog;
import io.github.palexdev.materialfx.font.MFXFontIcon;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;

public class DetailedOrderController {
    @FXML
    private MFXTextField addressText;

    @FXML
    private MFXTextField amountText;

    @FXML
    private MFXTextField dateReceiverText;

    @FXML
    private MFXTextField storeText;

    @FXML
    private MFXTextField sumText;

    @FXML
    private MFXTextField timeReceiverText;

    @FXML
    private MFXGenericDialog screen;

    @FXML
    private MFXFontIcon closeIcon;

    private Stage stage;

    private OrderClient orderToLook = null;



    private boolean overlayClose = false;



    @FXML
    void initialize(OrderClient order, Stage stage){
        this.stage = stage;
        double sum = 0;
        int amount = 0;
        this.setOrderToLook(order);

        ObservableList<CartItem> holder = orderToLook.getOrder_items();

        addressText.setText(orderToLook.getAddress());
        for(int i=0; i<holder.size();i++){
            amount = amount + holder.get(i).getItem_amount();
        }
        amountText.setText(Integer.toString(amount));
        dateReceiverText.setText(orderToLook.getOrderReceiveDate());
//        storeText.setText(orderToLook.getStoreChoosen());
        //
        for(int i=0; i<holder.size();i++){
            sum = sum + holder.get(i).getItem_price();
        }
        sumText.setText(Double.toString(sum));
        sumText.setText(Double.toString(order.getSum()));
        timeReceiverText.setText(orderToLook.getOrderReceiveTime());
    }

    public OrderClient getOrderToLook() {
        return orderToLook;
    }

    public void setOrderToLook(OrderClient orderToLook) {
        this.orderToLook = orderToLook;
    }

    @FXML
    void closeWindow(ActionEvent event) {
        this.stage.close();
    }
}



