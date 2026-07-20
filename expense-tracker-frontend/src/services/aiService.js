import api from "./api";

export const askAI = async (question) => {

    const response = await api.post("/ai/chat", {
        question: question
    });

    return response.data;
};

export const clearConversation = async () => {

    const response = await api.delete("/ai/conversation");

    return response.data;
};