package com.rajnishcodex.rajnishgem;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * A small, clean-room mobile workspace: a local chat shell, a terminal shell
 * and an About panel with the creator's social links.
 */
public class MainActivity extends Activity {

    private LinearLayout messageList;
    private ScrollView chatScroll;
    private LinearLayout chatPanel;
    private LinearLayout terminalPanel;
    private EditText messageInput;
    private final List<String> transcript = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setBackgroundColor(getColor(R.color.bg));

        TextView title = new TextView(this);
        title.setText(getString(R.string.app_name));
        title.setTextSize(22f);
        title.setTextColor(getColor(R.color.text_primary));
        title.setGravity(Gravity.CENTER);
        title.setPadding(24, 32, 24, 4);
        root.addView(title);

        TextView subtitle = new TextView(this);
        subtitle.setText("Clean-room mobile workspace");
        subtitle.setTextSize(13f);
        subtitle.setTextColor(getColor(R.color.text_muted));
        subtitle.setGravity(Gravity.CENTER);
        subtitle.setPadding(24, 0, 24, 20);
        root.addView(subtitle);

        LinearLayout tabs = new LinearLayout(this);
        tabs.setOrientation(LinearLayout.HORIZONTAL);
        tabs.setPadding(24, 0, 24, 12);
        Button chatTab = tabButton("Chat");
        Button terminalTab = tabButton("Terminal");
        Button aboutTab = tabButton("About");
        tabs.addView(chatTab, tabParams());
        tabs.addView(terminalTab, tabParams());
        tabs.addView(aboutTab, tabParams());
        root.addView(tabs);

        chatPanel = buildChatPanel();
        terminalPanel = buildTerminalPanel();

        LinearLayout content = new LinearLayout(this);
        content.setOrientation(LinearLayout.VERTICAL);
        content.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, 0, 1f));
        content.addView(chatPanel, new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        content.addView(terminalPanel, new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        terminalPanel.setVisibility(View.GONE);
        root.addView(content);

        chatTab.setOnClickListener(v -> showPanel(0));
        terminalTab.setOnClickListener(v -> showPanel(1));
        aboutTab.setOnClickListener(v -> showAbout());

        setContentView(root);
        addMessage("RajnishGem",
                "Local demo is ready. This minimal build does not connect to a live agent.");
    }

    private void showPanel(int index) {
        chatPanel.setVisibility(index == 0 ? View.VISIBLE : View.GONE);
        terminalPanel.setVisibility(index == 1 ? View.VISIBLE : View.GONE);
    }

    private LinearLayout buildChatPanel() {
        LinearLayout panel = new LinearLayout(this);
        panel.setOrientation(LinearLayout.VERTICAL);
        panel.setPadding(24, 0, 24, 24);

        chatScroll = new ScrollView(this);
        messageList = new LinearLayout(this);
        messageList.setOrientation(LinearLayout.VERTICAL);
        chatScroll.addView(messageList);
        panel.addView(chatScroll, new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, 0, 1f));

        LinearLayout row = new LinearLayout(this);
        row.setOrientation(LinearLayout.HORIZONTAL);
        messageInput = new EditText(this);
        messageInput.setHint("Message (local demo)");
        messageInput.setBackgroundColor(getColor(R.color.surface));
        messageInput.setPadding(16, 12, 16, 12);
        row.addView(messageInput, new LinearLayout.LayoutParams(0,
                ViewGroup.LayoutParams.WRAP_CONTENT, 1f));
        Button send = tabButton("Send");
        send.setOnClickListener(v -> sendMessage());
        row.addView(send);
        panel.addView(row);
        return panel;
    }

    private LinearLayout buildTerminalPanel() {
        LinearLayout panel = new LinearLayout(this);
        panel.setOrientation(LinearLayout.VERTICAL);
        panel.setPadding(24, 0, 24, 24);

        TextView term = new TextView(this);
        term.setTypeface(Typeface.MONOSPACE);
        term.setTextSize(13f);
        term.setTextColor(getColor(R.color.text_primary));
        term.setBackgroundColor(getColor(R.color.surface));
        term.setPadding(16, 16, 16, 16);
        term.setText("rajnish@gem:~$ echo 'RajnishGem'\n"
                + "RajnishGem\n"
                + "rajnish@gem:~$ _\n\n"
                + "Terminal is a visual scaffold in this minimal build.");
        panel.addView(term, new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, 0, 1f));
        return panel;
    }

    private void addMessage(String who, String text) {
        transcript.add(who + ": " + text);
        TextView line = new TextView(this);
        line.setText(who + "\n" + text);
        line.setTextColor(getColor(R.color.text_primary));
        line.setBackgroundColor(getColor(R.color.surface));
        line.setPadding(16, 12, 16, 12);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.setMargins(0, 0, 0, 12);
        messageList.addView(line, lp);
        chatScroll.post(() -> chatScroll.fullScroll(View.FOCUS_DOWN));
    }

    private void sendMessage() {
        String text = messageInput.getText().toString().trim();
        if (TextUtils.isEmpty(text)) {
            Toast.makeText(this, "Type a message first", Toast.LENGTH_SHORT).show();
            return;
        }
        addMessage("You", text);
        messageInput.setText("");
        addMessage("RajnishGem", "Echo: " + text + " (offline demo)");
    }

    private void showAbout() {
        new AlertDialog.Builder(this)
                .setTitle("RajnishGem")
                .setMessage("A clean-room minimal replica with creator links.")
                .setPositiveButton("Instagram",
                        (dialog, which) -> openLink("https://www.instagram.com/i_rajnishmaurya/"))
                .setNeutralButton("Telegram",
                        (dialog, which) -> openLink("https://t.me/rajnishcodex"))
                .setNegativeButton("GitHub",
                        (dialog, which) -> openLink("https://github.com/rajnishcodex"))
                .show();
    }

    private void openLink(String url) {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
    }

    private Button tabButton(String label) {
        Button button = new Button(this);
        button.setText(label);
        button.setTextSize(13f);
        button.setTextColor(getColor(R.color.on_accent));
        button.setBackgroundColor(getColor(R.color.accent));
        return button;
    }

    private LinearLayout.LayoutParams tabParams() {
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                0, ViewGroup.LayoutParams.WRAP_CONTENT, 1f);
        lp.setMargins(4, 0, 4, 0);
        return lp;
    }
}
