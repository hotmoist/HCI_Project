package com.example.hci_project.script;

public class MessageScript {
    private static final String[] MESSAGE = {
            "수면 살살 녹는다 ㅋㅋㅋㅋㅋㅋ",
            "당신의 수면 안녕하신지요?",
            "아 개피곤해",
            "니 몸이 살려달라고 난리다",
            "시력 -3... 안자면 더 떨어짐 ㅋ",
            "아 왜 안자냐? ",
            "좀 자라",
            "제발 이제 자야해... 안그러면 너 죽어...",
            "자고 싶지 않니?",
            "그만! 자!",
    };

    public static String getMessage(int index) {
        if (index >= MESSAGE.length) {
            return "나.. 에러났어... 씨ㅣㅂ";
        }
        return MESSAGE[index];
    }
}
