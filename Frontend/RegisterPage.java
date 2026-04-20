import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Registration screen for EcoAudit.
 *
 * Authentication stub:
 *   registerUser(loginId, password) — to be implemented by the JDBC developer.
 *   It should INSERT a new row into the `users` table and return true on success.
 */
public class RegisterPage extends JFrame {

    private static final long serialVersionUID = 1L;

    public RegisterPage() {
        setTitle("EcoAudit 🌱 – Register");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // ── Background ──────────────────────────────────────────────────────
        JPanel bgPanel = new JPanel(new BorderLayout());
        bgPanel.setBackground(new Color(220, 230, 230));

        ImagePanel card = new ImagePanel("/assets/map_bg.png");
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
        card.setOpaque(false);

        // ── Glass card ───────────────────────────────────────────────────────
        GlassPanel content = new GlassPanel();
        content.setAlignmentX(Component.CENTER_ALIGNMENT);
        content.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));
        content.setMaximumSize(new Dimension(500, 700));

        // ── Heading ──────────────────────────────────────────────────────────
        JLabel heading = new JLabel("Create Account");
        heading.setFont(new Font("Segoe UI", Font.BOLD, 28));
        heading.setForeground(new Color(27, 94, 32));
        heading.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel sub = new JLabel("Join the green journey 🌿");
        sub.setFont(new Font("Segoe UI Semibold", Font.ITALIC, 15));
        sub.setForeground(new Color(56, 142, 60));
        sub.setAlignmentX(Component.CENTER_ALIGNMENT);

        // ── Fields ───────────────────────────────────────────────────────────
        Dimension fieldDim = new Dimension(350, 45);

        JTextField loginIdField = createPlaceholderField("Login ID", fieldDim);
        JPasswordField passwordField = createPlaceholderPassword("Password", fieldDim);
        JPasswordField confirmField = createPlaceholderPassword("Confirm Password", fieldDim);

        // ── Status label ─────────────────────────────────────────────────────
        JLabel statusMsg = new JLabel(" ");
        statusMsg.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
        statusMsg.setAlignmentX(Component.CENTER_ALIGNMENT);

        // ── Register button ──────────────────────────────────────────────────
        JButton registerBtn = new RoundedButton("Register");
        registerBtn.setMaximumSize(new Dimension(350, 50));
        registerBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

        registerBtn.addActionListener(e -> {
            String loginId = loginIdField.getText().trim();
            String pass    = new String(passwordField.getPassword());
            String confirm = new String(confirmField.getPassword());

            // Basic validation
            if (loginId.isEmpty() || loginId.equals("Login ID")) {
                showStatus(statusMsg, "Login ID cannot be empty ❌", Color.RED);
                return;
            }
            if (pass.isEmpty() || pass.equals("Password")) {
                showStatus(statusMsg, "Password cannot be empty ❌", Color.RED);
                return;
            }
            if (!pass.equals(confirm)) {
                showStatus(statusMsg, "Passwords do not match ❌", Color.RED);
                return;
            }

            // ── JDBC stub ────────────────────────────────────────────────────
            boolean success = registerUser(loginId, pass);
            // ─────────────────────────────────────────────────────────────────

            if (success) {
                showStatus(statusMsg, "Account created! Redirecting… ✅", new Color(46, 125, 50));
                Timer t = new Timer(1000, ev -> {
                    dispose();
                    new WelcomePage(false);
                });
                t.setRepeats(false);
                t.start();
            } else {
                showStatus(statusMsg, "Registration failed. ID may already exist ❌", Color.RED);
            }
        });

        // ── Back to login link ───────────────────────────────────────────────
        JLabel backLabel = new JLabel("Already have an account? Sign in");
        backLabel.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        backLabel.setForeground(new Color(33, 150, 243));
        backLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        backLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        backLabel.addMouseListener(new MouseAdapter() {
            @Override public void mouseClicked(MouseEvent e) {
                dispose();
                new WelcomePage(false);
            }
        });

        // ── Assemble card ────────────────────────────────────────────────────
        content.add(heading);
        content.add(Box.createVerticalStrut(8));
        content.add(sub);
        content.add(Box.createVerticalStrut(30));
        content.add(loginIdField);
        content.add(Box.createVerticalStrut(15));
        content.add(passwordField);
        content.add(Box.createVerticalStrut(15));
        content.add(confirmField);
        content.add(Box.createVerticalStrut(10));
        content.add(statusMsg);
        content.add(Box.createVerticalStrut(10));
        content.add(registerBtn);
        content.add(Box.createVerticalStrut(20));
        content.add(backLabel);

        card.add(Box.createVerticalGlue());
        card.add(content);
        card.add(Box.createVerticalGlue());

        bgPanel.add(card, BorderLayout.CENTER);
        add(bgPanel);
        setVisible(true);
    }

    private boolean registerUser(String loginId, String password) {
        // Try MySQL first
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            java.sql.Connection con = java.sql.DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/eco_audit", "root", "root123");
            java.sql.PreparedStatement ps = con.prepareStatement(
                "INSERT INTO users (login_id, password, total_score, level) VALUES (?, ?, 0, 1)");
            ps.setString(1, loginId);
            ps.setString(2, password);
            ps.executeUpdate();
            con.close();
            return true;
        } catch (Exception ex) {
            // No DB — fall back to local file storage
        }

        // File-based fallback: store in users.json
        try {
            java.io.File file = new java.io.File("users.json");
            StringBuilder sb = new StringBuilder();

            // Read existing users
            if (file.exists()) {
                java.util.Scanner sc = new java.util.Scanner(file);
                while (sc.hasNextLine()) sb.append(sc.nextLine());
                sc.close();
            }

            String existing = sb.toString().trim();

            // Check if loginId already exists
            if (existing.contains("\"" + loginId + "\"")) {
                return false; // already registered
            }

            // Build new entry
            String newEntry = "{\"id\":\"" + loginId + "\",\"pass\":\"" + password + "\"}";

            // Append to JSON array
            String updated;
            if (existing.isEmpty() || existing.equals("[]")) {
                updated = "[" + newEntry + "]";
            } else {
                // insert before closing ]
                updated = existing.substring(0, existing.lastIndexOf(']')) + "," + newEntry + "]";
            }

            java.io.FileWriter fw = new java.io.FileWriter(file);
            fw.write(updated);
            fw.close();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /** Called by WelcomePage to validate login against file store */
    public static boolean validateLogin(String loginId, String password) {
        // Try MySQL first
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            java.sql.Connection con = java.sql.DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/eco_audit", "root", "root123");
            java.sql.PreparedStatement ps = con.prepareStatement(
                "SELECT login_id FROM users WHERE login_id=? AND password=?");
            ps.setString(1, loginId);
            ps.setString(2, password);
            java.sql.ResultSet rs = ps.executeQuery();
            boolean found = rs.next();
            con.close();
            return found;
        } catch (Exception ex) {
            // No DB — check file
        }

        try {
            java.io.File file = new java.io.File("users.json");
            if (!file.exists()) return false;
            java.util.Scanner sc = new java.util.Scanner(file);
            StringBuilder sb = new StringBuilder();
            while (sc.hasNextLine()) sb.append(sc.nextLine());
            sc.close();
            String content = sb.toString();
            // Simple check: look for matching id+pass pair
            return content.contains("\"id\":\"" + loginId + "\",\"pass\":\"" + password + "\"");
        } catch (Exception e) {
            return false;
        }
    }

    static String sessionUser = null;
    static String sessionPass = null;

    private void showStatus(JLabel label, String msg, Color color) {
        label.setText(msg);
        label.setForeground(color);
    }

    // ── Reusable field helpers ────────────────────────────────────────────────
    private JTextField createPlaceholderField(String placeholder, Dimension size) {
        JTextField field = new JTextField(placeholder);
        field.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        field.setForeground(Color.GRAY);
        field.setMaximumSize(size);
        field.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
            BorderFactory.createEmptyBorder(5, 15, 5, 15)));
        field.addFocusListener(new FocusAdapter() {
            @Override public void focusGained(FocusEvent e) {
                if (field.getText().equals(placeholder)) {
                    field.setText(""); field.setForeground(Color.BLACK);
                }
            }
            @Override public void focusLost(FocusEvent e) {
                if (field.getText().isEmpty()) {
                    field.setText(placeholder); field.setForeground(Color.GRAY);
                }
            }
        });
        return field;
    }

    private JPasswordField createPlaceholderPassword(String placeholder, Dimension size) {
        JPasswordField field = new JPasswordField(placeholder);
        field.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        field.setForeground(Color.GRAY);
        field.setEchoChar((char) 0);
        field.setMaximumSize(size);
        field.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
            BorderFactory.createEmptyBorder(5, 15, 5, 15)));
        field.addFocusListener(new FocusAdapter() {
            @Override public void focusGained(FocusEvent e) {
                if (String.valueOf(field.getPassword()).equals(placeholder)) {
                    field.setText(""); field.setForeground(Color.BLACK); field.setEchoChar('•');
                }
            }
            @Override public void focusLost(FocusEvent e) {
                if (String.valueOf(field.getPassword()).isEmpty()) {
                    field.setText(placeholder); field.setForeground(Color.GRAY); field.setEchoChar((char) 0);
                }
            }
        });
        return field;
    }

    // ── Inner panels (same as WelcomePage) ───────────────────────────────────
    class ImagePanel extends JPanel {
        private static final long serialVersionUID = 1L;
        private Image image;
        public ImagePanel(String path) {
            image = new ImageIcon(getClass().getResource(path)).getImage();
        }
        @Override protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
        }
    }

    class GlassPanel extends JPanel {
        private static final long serialVersionUID = 1L;
        public GlassPanel() {
            setOpaque(false);
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        }
        @Override protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(new Color(255, 255, 255, 180));
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 40, 40);
            g2.setColor(new Color(255, 255, 255, 200));
            g2.setStroke(new BasicStroke(2));
            g2.drawRoundRect(2, 2, getWidth() - 4, getHeight() - 4, 40, 40);
            g2.setColor(new Color(0, 0, 0, 20));
            g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 40, 40);
            g2.dispose();
            super.paintComponent(g);
        }
    }
}
