package org.example.team.complaint.controller;

import org.example.team.TeamController;
import org.example.common.dto.RequestVO;
import org.example.team.complaint.ComplaintManagementTeam;
import org.example.team.complaint.usecase.ComplaintUseCase;
import org.example.team.complaint.view.ComplaintView;

public class ComplaintController implements TeamController {

    private final ComplaintView complaintView;
    private final ComplaintManagementTeam complaintManagementTeam;

    public ComplaintController(ComplaintView complaintView, ComplaintManagementTeam complaintManagementTeam) {
        this.complaintView = complaintView;
        this.complaintManagementTeam = complaintManagementTeam;
    }

    @Override
    public void process() {
        complaintView.intro("민원 관리");
        int selectNumber = complaintView.selectUsecase(ComplaintUseCase.class);
        ComplaintUseCase useCase = ComplaintUseCase.findByNumber(selectNumber);
        RequestVO requestVO = showUseCaseRequireInfo(useCase);
        complaintManagementTeam.register(requestVO);
    }

    private RequestVO showUseCaseRequireInfo(ComplaintUseCase useCase) {
        switch (useCase) {
            case REGISTER_COMPLAINT -> {
                return complaintView.registerComplaint();
            }
            case REQUEST_PROCESSING_STATUS -> {
                return null; // 아직 고민 중
            }
            case EVALUATE_COMPLAINT -> {
                return complaintView.evaluateComplaint();
            }
            default -> throw new IllegalArgumentException("해당 유스케이스 번호는 존재하지 않습니다.");
        }
    }
}
