export default function QARatio({ user, span = false }) {

    return span ?
        <span className="qa-ratio" title="The Question/Answer ratio.">
            <span>{user.questions}</span> / <span>{user.answers}</span>
        </span>
        :
        <div className="qa-ratio" title="The Question/Answer ratio.">
            <span>{user.questions}</span> / <span>{user.answers}</span>
        </div>
}