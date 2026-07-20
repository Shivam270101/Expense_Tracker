import api from "./api";

export const askAI = (question) =>
  api.post("/ai/chat", {
    question,
  });
