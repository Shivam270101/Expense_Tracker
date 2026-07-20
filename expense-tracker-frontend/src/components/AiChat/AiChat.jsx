import { useState } from "react";
import { askAI } from "../../services/aiService";
import "./AiChat.css";

export default function AiChat() {
  const [question, setQuestion] = useState("");
  const [answer, setAnswer] = useState("");
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState("");

  const handleAsk = async () => {
    if (!question.trim()) return;
    setLoading(true);
    setError("");
    try {
      const { data } = await askAI(question);
      setAnswer(data.response);
    } catch (err) {
      setError("Something went wrong. Please try again.");
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="ai-chat">
      <textarea
        className="ai-chat-input"
        placeholder="Ask anything about your finances..."
        value={question}
        onChange={(e) => setQuestion(e.target.value)}
        rows={4}
      />
      <button className="ai-chat-ask" onClick={handleAsk} disabled={loading}>
        {loading ? "Thinking..." : "Ask AI"}
      </button>

      {loading && (
        <div className="ai-thinking">
          🤖 AI is thinking...
        </div>
      )}

      {error && <p className="ai-chat-error">{error}</p>}

      {answer && (
        <div className="ai-chat-response">
          <strong>AI Response:</strong>
          <p>{answer}</p>
        </div>
      )}
    </div>
  );
}
