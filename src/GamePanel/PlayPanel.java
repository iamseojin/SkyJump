package GamePanel;

import GameObject.WallObject;

import java.awt.*;
import java.util.*;
import java.util.List;

public class PlayPanel extends BackgroudPanel{
    // n번째 stage
    int stage = 0;

    // 전체 스테이지 디자인
    List<List<Point>> mapDesign = new ArrayList<>();

    // 스테이지에서 사용할 디자인 (mapDesign에서 랜덤 3개 + 바닥이 될 7번 디자인)
    List<Integer> stageMap = new ArrayList<>();

    // 화면 내에서 밟을 수 있는 블록, 스테이지마다 초기화
    List<WallObject> block = new ArrayList<>();
    // 플레이 화면 범위를 설정하는 벽
    List<WallObject> wall = new ArrayList<>();

    public PlayPanel(int w, int h) {
        super(w, h);

        setMapDesign();
        setWall();
        setStage();
    }

    private void setStage() {
        setStageMap();
        setBlock();
    }

    private void setWall() {
        // 천장
        wall.add(new WallObject(1, 1, width - 15, 10));
        // 왼쪽 벽
        wall.add(new WallObject(1, 11, 10, height - 49));
        // 오른쪽 벽
        wall.add(new WallObject(width - 32, 11, 11, height - 49));
        // 바닥
        wall.add(new WallObject(1, height - 105, width - 15, 50));
        //wall.add(new WallObject(1, 120, width - 15, 50));
        //wall.add(new WallObject(1, 310, width - 15, 50));
        //wall.add(new WallObject(1, 510, width - 15, 50));
    }

    private void setBlock() {
        /*Queue<Integer> blockY = new LinkedList<>();
        blockY.add(130);
        blockY.add(315);
        blockY.add(510);
        blockY.add(695);*/
        List<Integer> blockY = new ArrayList<>();
        blockY.add(130);
        blockY.add(315);
        blockY.add(510);
        blockY.add(695);

        for (int i=0; i<3; i++) {
            for (Point p : mapDesign.get(stageMap.get(i))) {
                if (blockY.isEmpty()) break;
                block.add(new WallObject(p.x, blockY.get(i), p.y, 50));
            }
        }
    }

    // 전체 블록 디자인 틀
    private void setMapDesign() {
        for (int i=0; i<8; i++) {
            mapDesign.add(new ArrayList<>());
        }

        mapDesign.get(0).add(new Point(0, 200));
        mapDesign.get(0).add(new Point(300, 200));
        mapDesign.get(0).add(new Point(600, 200));

        mapDesign.get(1).add(new Point(200, 300));
        mapDesign.get(1).add(new Point(700, 200));

        mapDesign.get(2).add(new Point(0, 200));
        mapDesign.get(2).add(new Point(500, 300));

        mapDesign.get(3).add(new Point(0, 300));
        mapDesign.get(3).add(new Point(500, 200));

        mapDesign.get(4).add(new Point(100, 200));
        mapDesign.get(4).add(new Point(400, 300));

        mapDesign.get(5).add(new Point(0, 600));
        mapDesign.get(5).add(new Point(700, 100));

        mapDesign.get(6).add(new Point(0, 100));
        mapDesign.get(6).add(new Point(200, 500));
    }

    // mapDesign에서 3개 선택, 7번째는 바닥.
    private void setStageMap() {
        Random random = new Random();

        Set<Integer> stageMapSet = new HashSet<>();
        while (stageMapSet.size() < 3) {
            stageMapSet.add(random.nextInt(7));
        }

        stageMap.addAll(stageMapSet);
        Collections.shuffle(stageMap);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (WallObject w : wall) {
            w.draw(g);
        }

        for (WallObject b : block) {
            b.draw(g);
        }
    }
}
