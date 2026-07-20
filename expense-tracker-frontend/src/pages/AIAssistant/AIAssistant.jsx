import MainLayout from "../../components/MainLayout";
import AiChat from "../../components/AiChat/AiChat";
import "./AIAssistant.css";

export default function AIAssistant() {
  return (
    <MainLayout>
      <div className="ai-assistant-page">
        <AiChat />
      </div>
    </MainLayout>
  );
}