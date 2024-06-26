package org.example.team.customerManagement.controller;


import org.example.team.TeamController;
import org.example.team.customerManagement.CustomerManageTeam;
import org.example.team.customerManagement.usecase.CustomerManagementUsecase;
import org.example.team.customerManagement.view.CustomerManagementView;

public class CustomerManagementController implements TeamController {

    private final CustomerManageTeam customerManageTeam;
    private final CustomerManagementView customerManagementView;

    public CustomerManagementController(CustomerManageTeam customerManageTeam, CustomerManagementView customerManagementView) {
        this.customerManageTeam = customerManageTeam;
        this.customerManagementView = customerManagementView;
    }

    @Override
    public void process() {
        customerManagementView.intro("고객 관리 팀");
        int selectNumber = customerManagementView.selectUsecase(CustomerManagementUsecase.class);
        CustomerManagementUsecase usecase = CustomerManagementUsecase.findByNumber(selectNumber);
        executeUsecase(usecase);
    }

    private void executeUsecase(CustomerManagementUsecase usecase) {
        switch (usecase) {
            case ALL_CUSTOMER_RETRIEVE -> {
                customerManagementView.showAllCustomerInfo(customerManageTeam.retrieveCustomerInfo());
            }
            default -> throw new IllegalArgumentException("해당하는 usecase가 없습니다.");
        }
    }
}
