import MainLayout from "../../components/MainLayout";
import useAuth from "../../hooks/useAuth";

export default function Profile() {
  const { user } = useAuth();

  return (
    <MainLayout>
      <h2>Profile</h2>
      <div
        style={{
          background: "#fff",
          border: "1px solid #e5e7eb",
          borderRadius: 10,
          padding: 20,
          maxWidth: 400,
        }}
      >
        <p>
          <strong>Full Name:</strong> {user?.fullName || "-"}
        </p>
        <p>
          <strong>Email:</strong> {user?.email || "-"}
        </p>
      </div>
    </MainLayout>
  );
}
