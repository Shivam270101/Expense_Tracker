import { useEffect, useRef, useState } from "react";
import { askAI, clearConversation } from "../../services/aiService";
import ReactMarkdown from "react-markdown";
import "./AiChat.css";

const suggestions = [
  "How much did I spend this month?",
  "Which category has the highest expense?",
  "Give me saving tips",
  "How much budget is remaining?"
];

export default function AiChat() {

  const [input, setInput] = useState("");

  const [messages, setMessages] = useState([]);

  const [loading, setLoading] = useState(false);

  const messagesEndRef = useRef(null);

  useEffect(() => {
    scrollToBottom();
  }, [messages, loading]);

  const scrollToBottom = () => {
    messagesEndRef.current?.scrollIntoView({
      behavior: "smooth"
    });
  };

  const sendMessage = async (question) => {

    if (!question.trim()) return;

    setMessages(prev => [
      ...prev,
      {
        role: "user",
        content: question
      }
    ]);

    setInput("");

    setLoading(true);

    try {

      const response = await askAI(question);

      setMessages(prev => [
        ...prev,
        {
          role: "assistant",
          content: response.response
        }
      ]);

    } catch {

      setMessages(prev => [
        ...prev,
        {
          role: "assistant",
          content: "Something went wrong."
        }
      ]);

    } finally {

      setLoading(false);

    }

  };

  const handleSubmit = () => {

    sendMessage(input);

  };

  const handleSuggestion = (question) => {

    sendMessage(question);

  };

  const handleNewChat = async () => {

    await clearConversation();

    setMessages([]);

    setInput("");

  };

  const handleKeyDown = (e) => {

    if (e.key === "Enter" && !e.shiftKey) {

      e.preventDefault();

      handleSubmit();

    }

  };

  return (

    <div className="ai-chat">

      <div className="ai-chat-header">

        <h2>🤖 FinMate - Your AI Finance Assistant</h2>

        <button
          className="new-chat-btn"
          onClick={handleNewChat}
        >
          + New Chat
        </button>

      </div>

      <div className="chat-body">

        {messages.length === 0 && (

          <div className="welcome">

            <h2>👋 Welcome</h2>

            <p>
              Ask anything about your expenses,
              budgets and savings.
            </p>

            <div className="suggestions">

              {suggestions.map((item, index) => (

                <button
                  key={index}
                  onClick={() => handleSuggestion(item)}
                >
                  {item}
                </button>

              ))}

            </div>

          </div>

        )}

        {messages.map((message, index) => (

          <div
            key={index}
            className={`message ${message.role}`}
          >

            <div className="avatar">

              {message.role === "user"
                ? "👤"
                : "🤖"}

            </div>

            <div className="message-content">
                <ReactMarkdown>
                  {message.content}
                </ReactMarkdown>
            </div>

          </div>

        ))}

       {loading && (
        <div className="message assistant">
          <div className="avatar">🤖</div>
          <div className="message-content thinking">
            <span></span>
            <span></span>
            <span></span>
          </div>
        </div>
      )}

        <div ref={messagesEndRef}></div>

      </div>

      <div className="chat-input">

        <textarea

          placeholder="Ask anything..."

          value={input}

          onChange={(e) => setInput(e.target.value)}

          onKeyDown={handleKeyDown}

          rows={2}

        />

        <button

          onClick={handleSubmit}

          disabled={loading}

        >
          Send
        </button>

      </div>

    </div>

  );

}