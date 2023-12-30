package Controllers;
import Models.Group;
import java.util.List;
import DAO.GroupDAO;
public class GroupController {
    private final GroupDAO groupDAO;

    public GroupController() {
        this.groupDAO = new GroupDAO();
    }

    public boolean createGroup(Group group) {
        return groupDAO.createGroup(group);
    }

    public boolean deleteGroup(int groupId) {
        return groupDAO.deleteGroup(groupId);
    }

    public List<Group> getAllGroups() {
        return groupDAO.getAllGroups();
    }
}
