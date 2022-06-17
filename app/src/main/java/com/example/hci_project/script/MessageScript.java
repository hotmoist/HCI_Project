package com.example.hci_project.script;

public class MessageScript {
    private static final String[] MESSAGE = {
            "수면 살살 녹는다 ㅋㅋㅋㅋㅋㅋ",
            "당신의 수면 안녕하신지요?",
            "30초만 눈 감고있으면 안될까?",
            "니 몸이 살려달라고 난리다",
            "양 한마리 양 두마리 양 세마리 양 네마리...",
            "오늘 할 일을 내일로 미뤄라",
            "나폴레옹도 너보단 많이 잤다",
            "제발 이제 자야해... 안그러면 너 죽어...",
            "잠은 죽어서도 잘 수 있다. 하지만 안자면 단명한다",
            "그만! 자!",
    };

    public static String getMessage(int index) {
        if (index >= MESSAGE.length) {
            return "나.. 에러났어... 씨ㅣㅂ";
        }
        return MESSAGE[index];
    }
}
