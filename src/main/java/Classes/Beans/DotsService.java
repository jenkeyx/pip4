package Classes.Beans;

import Classes.Spring.Data.DotRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DotsService {
    DotRepo dotRepo;

    @Autowired
    public DotsService(DotRepo dotRepo) {
        this.dotRepo = dotRepo;
    }

    public List<Dot> getDotsByUsername(String username) {
        return dotRepo.findAllByOwner(username);
    }

    public void saveDot(Dot dot) {
        dot.setHit(checkArea(dot));
        dotRepo.save(dot);
    }

    public static boolean checkArea(Dot dot) {
        double x = dot.x;
        double y = dot.y;
        double r = dot.r;
        return (x >= 0 && y >= 0 && x <= r && y <= r / 2) || (x <= 0 && y >= 0 && r * r >= x * x + y * y) || (x <= 0 && y <= 0 && y >= -x - r / 2);
    }

}