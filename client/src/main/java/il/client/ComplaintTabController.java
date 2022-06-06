package il.client;

import il.client.DiffClasses.ComplaintClient;
import il.client.controls.CatalogControl;
import il.client.controls.ComplainConrtol;
import il.client.events.ComplaintEvent;
import il.entities.Complain;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.legacy.MFXLegacyTableView;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class ComplaintTabController {

    @FXML
    private TableColumn<ComplaintClient, Integer> complaint_id_col;

    @FXML
    private TableColumn<ComplaintClient, String> date_col;

    @FXML
    private TableColumn<ComplaintClient, MFXButton> handle_col;

    @FXML
    private MFXLegacyTableView<ComplaintClient> complaints_table;

    @FXML
    private TableColumn<ComplaintClient, String> time_col;

    ObservableList<ComplaintClient> items = FXCollections.observableArrayList();

    private MyAccountController my_account_page_holder;

    @FXML
    void initialize() throws IOException {
        EventBus.getDefault().register(this);
       ComplainConrtol.getAllOpenComplaintsByUser(7);
        TableInitializeFields();
//        items = UserClient.getInstance().getComplaintList();
//        for(int i=0; i< items.size(); i++){
//            System.out.println(items.get(i));
        }
    @Subscribe
    public void setComplaintList(ComplaintEvent event) {
        Platform.runLater(() -> {
            List<Complain> tempList = event.getComplainList();
            System.out.println(tempList);
            ObservableList<ComplaintClient> castList = FXCollections.observableArrayList();
            castList.addAll((ComplaintClient) tempList);
            System.out.println(castList);
            UserClient.getInstance().setComplaintList(castList);
            complaints_table.setItems(UserClient.getInstance().getComplaintList());
       });
    }



    //
    public void TableInitializeFields() {
        complaints_table.setFixedCellSize(40);
        complaint_id_col.setCellValueFactory(new PropertyValueFactory<ComplaintClient, Integer>("this_id"));
        date_col.setCellValueFactory(new PropertyValueFactory<ComplaintClient, String>("complaintDate"));
        time_col.setCellValueFactory(new PropertyValueFactory<ComplaintClient, String>("ComplaintTime"));

        complaints_table.setRowFactory(s->{
            TableRow<ComplaintClient> row = new TableRow<ComplaintClient>();
            row.setOnMouseClicked(mouseEvent -> {
                        System.out.println(row.getItem());
                        try {
                            HandelComplaintScreen(row);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
            );
            return row;
        });
    }

    public void HandelComplaintScreen(TableRow<ComplaintClient> row) throws IOException {
        ComplaintClient complaint = UserClient.getInstance().getComplaintById(row.getItem().getThis_id());
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("ComplainHandler.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 698 , 794);
        ComplainHandlerController controller = fxmlLoader.getController();
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setTitle("Complaint Handler");
        stage.setScene(scene);
        stage.show();
        controller.initialize(complaint,stage);
    }

    /* gets and sets*/

    public MyAccountController getMy_account_page_holder() {
        return my_account_page_holder;
    }

    public void setMy_account_page_holder(MyAccountController my_account_page_holder) {
        this.my_account_page_holder = my_account_page_holder;
    }

    /*end sets and gets*/
}